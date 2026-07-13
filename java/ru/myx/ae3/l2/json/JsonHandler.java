package ru.myx.ae3.l2.json;

import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.l2.ContextHandler;

interface JsonHandler extends ContextHandler<JsonTargetContext, BaseObject> {
	void insert(final JsonTargetContext target, final BaseObject value);
}
