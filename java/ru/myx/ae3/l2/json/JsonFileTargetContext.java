package ru.myx.ae3.l2.json;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import ru.myx.ae3.ecma.Ecma;
import ru.myx.ae3.i3.TargetInterface;
import ru.myx.ae3.l2.NativeTargetContext;

/** @author myx */
public class JsonFileTargetContext extends JsonTargetContext {

	private final File file;
	
	/** @param targetMode
	 * @param file
	 */
	public JsonFileTargetContext(final NativeTargetContext.TargetMode targetMode, final File file) {
		
		super((TargetInterface) null, targetMode);
		this.file = file;
	}
	
	@Override
	public void doFinish() {

		super.doFinish();
		try (final RandomAccessFile output = new RandomAccessFile(this.file, "rw")) {
			output.write(
					(this.result == null
						? "null"
						: Ecma.toEcmaSource(this.result, false, 0)).getBytes(StandardCharsets.UTF_8));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
