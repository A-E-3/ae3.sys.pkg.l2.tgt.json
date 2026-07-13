package ru.myx.ae3.l2.json;

import ru.myx.ae3.answer.Reply;
import ru.myx.ae3.answer.ReplyAnswer;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.l2.NativeTargetContext;
import ru.myx.ae3.l2.TargetContext;
import ru.myx.ae3.serve.ServeRequest;

/** @author myx */
public class JsonReplyTargetContext extends JsonTargetContext {
	
	private final ServeRequest query;
	
	/** @param targetMode
	 * @param query
	 */
	public JsonReplyTargetContext(final NativeTargetContext.TargetMode targetMode, final ServeRequest query) {
		
		super((TargetContext<?>) null, targetMode);
		this.query = query;
	}
	
	@Override
	public void doFinish() {
		
		super.doFinish();
		final int code = Base.getInt(this.result, "code", 0);
		try {
			final ReplyAnswer reply = Reply.string(
					"L2-JSON",
					this.query,
					this.result == null
						? "null"
						: Ecma.toEcmaSource(this.result, false, 0))//
					.setCode(
							code < 100 || code >= 600
								? 200
								: code)
					.setNoCaching().setFinal();
			this.query.getResponseTarget().apply(reply);
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
