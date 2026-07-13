package ru.myx.ae3.l2.json;

import ru.myx.ae3.i3.TargetInterface;
import ru.myx.ae3.l2.NativeTargetContext;
import ru.myx.ae3.l2.TargetContext;

/** @author myx */
public class JsonTargetContext extends NativeTargetContext {
	
	/** @param replacement
	 * @param targetMode
	 */
	public JsonTargetContext(final TargetContext<?> replacement, final NativeTargetContext.TargetMode targetMode) {
		
		super(replacement, targetMode);
	}
	
	/** @param iface
	 * @param targetMode
	 */
	public JsonTargetContext(final TargetInterface iface, final NativeTargetContext.TargetMode targetMode) {
		
		super(iface, targetMode);
	}
	
}
