# CLAUDE.md — ae3.sys.pkg.l2.tgt.json

AE3 L2 media target for JSON output.

## Structure

- `java/ru/myx/ae3/l2/json/`
  - `JsonTargetContext extends NativeTargetContext` — the base target-context.
  - `JsonFileTargetContext extends JsonTargetContext` — file-output variant (mirrors `ae3.sys.pkg.l2.tgt.xml`'s `XmlFileTargetContext`).
  - `JsonReplyTargetContext extends JsonTargetContext` — direct-reply-output variant.
  - `JsonHandler extends ContextHandler<JsonTargetContext, BaseObject>` — context-handler interface.
  - `WebContextJson extends NativeTargetContext implements ru.myx.ae3.i3.web.WebContext<NativeTargetContext>` — the HTTP-reply-producing adapter for the `WebContextType`/`WebContextOutputRegistry` dispatch system, moved here from `ae3.sys.pkg.i3.web` (see that unit's CLAUDE.md for why: every `WebContext*` class now lives with the target-context it wraps). Registered for `json` via `ae3-packages/ae3.sys.l2.tgt.json/settings/system/l3/targets/json.json`. **Distinct from `JsonTargetContext`/`JsonReplyTargetContext` below** — despite similar names and both producing JSON HTTP replies, they're unrelated, parallel mechanisms: `WebContextJson` only goes through `WebContextType`'s content-negotiation dispatch, while `JsonReplyTargetContext` is invoked directly by other L2 target code (see next paragraph) and has a different constructor shape (`(TargetMode, ServeRequest)`, not `WebContext`'s `(TargetInterface, ServeRequest)`) — it doesn't implement `WebContext` at all. Don't conflate the two or assume a shared code path.
  - `test/TestJson.java` — manual smoke-test entry point, same shape as this family's other `Test*` classes.

Other units in this session construct `JsonTargetContext` directly (e.g. `ae3.sys.pkg.l2.tgt.dhtml`'s `DhtmlCommon` uses `new JsonTargetContext(original, NativeTargetContext.TargetMode.SERVER)` to serialize a target's data object to a `.jsld` file for its JS-client bootstrap) — this unit isn't only reached via HTTP content negotiation, it's also used as a building block by other L2 targets.

## Build

- Requires (Java): `ae3.sdk`, and now `ae3.web` too (added for `WebContextJson` above — needs the `ru.myx.ae3.i3.web.WebContext` interface; `.classpath` needs a matching `path="/ae3.sys.pkg.i3.web"` `classpathentry`, added alongside). Every other cross-package import resolves to `ru.myx.ae3.answer.*`/`ru.myx.ae3.i3.TargetInterface`/`ru.myx.ae3.serve.*` (`ae3.api`) or `ru.myx.ae3.l2.*`/`ru.myx.ae3.Engine` (`ae3.sdk`), both reachable the same way `ae3.sys.pkg.l2.tgt.html` reaches them (see that unit's CLAUDE.md for the same "project.inf needs less than .classpath" observation).
- `ru.myx.ae3.ecma.Ecma` lives in `ae3.api` (not `ae3.sdk` — its `ecma/compare/` subpackage there is a different thing). Already covered transitively via `ae3.sdk`'s own `Requires: ae3.api`.
- `package.json` (`ae3-packages/ae3.sys.l2.tgt.json/package.json`): name `ae3.sys.l2.tgt.json`, description "AE3 L2 Media Target: JSON", `"requires": "ae3.base"` (JS/`ae3-packages` module system — separate from the Java `project.inf` graph, see `ae3.sys.pkg.l2.tgt.dhtml`'s CLAUDE.md for why these shouldn't be assumed identical).

## Gotchas

- **This unit had no `.gitignore`/`.project`/`.classpath`/`project.inf` at all** when checked out this session — all four added fresh, modeled on `ae3.sys.pkg.l2.tgt.html`'s versions.
- **`project.inf`'s `Provides: ae3.sys.l2.tgt.json`** matches this unit's `package.json` `name` field exactly (the one corroborated naming signal available — see `ae3.sys.pkg.l2.tgt.html`'s CLAUDE.md for the fuller discussion of why the `l2.tgt.xml` three-entry `Provides:` shape turned out to be that one file's own choice, not a system-wide convention, once compared against every other real `project.inf` in the workspace).
- **6 compiled `.class` files are checked into git under `bin/`** — not yet cleaned up (same pre-existing hygiene issue as the other `l2.tgt.*` units touched this session).
