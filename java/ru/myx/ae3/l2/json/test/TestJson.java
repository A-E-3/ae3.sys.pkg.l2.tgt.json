package ru.myx.ae3.l2.json.test;

import java.io.FileInputStream;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.BaseObject;
import ru.myx.ae3.l2.LayoutEngine;
import ru.myx.ae3.l2.json.JsonReplyTargetContext;
import ru.myx.ae3.serve.file.ServeRequestExecuteFileOnReply;

/**
 * @author myx
 * 
 */
public class TestJson {
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(final String[] args) throws Throwable {
		Engine.createGuid();
		
		final ServeRequestExecuteFileOnReply query = new ServeRequestExecuteFileOnReply( "jsontest-", ".js" );
		System.out.println( "LayoutEngine2 JSON renderer test, output to: " + query );
		
		final BaseObject text = args == null || args.length == 0
				? LayoutEngine.getDocumentation() // context.getLayoutAbout()
				: args.length > 1
						? LayoutEngine.getDocumentation()
						: LayoutEngine.parseJSLD( new FileInputStream( args[0] ) );
		
		new JsonReplyTargetContext( null, query ).transform( text ).baseValue();
	}
}
