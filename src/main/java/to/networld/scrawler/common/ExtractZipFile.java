/**
 * scrawler - to.networld.scrawler.common
 *
 * Copyright (C) 2010 by Networld Project
 * Written by Alex Oberhauser <alexoberhauser@networld.to>
 * All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>
 */

package to.networld.scrawler.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Alex Oberhauser
 */
public abstract class ExtractZipFile {
	
	private static void copyFile(InputStream _is, OutputStream _os) throws IOException {
	    byte[] buffer = new byte[1024];
	    int chars;

	    while( (chars = _is.read(buffer) ) >= 0)
	    	_os.write(buffer, 0, chars);

	    _is.close();
	    _os.close();
	}
	
	public static void extractZIPFile(ZipFile _zipFile, File _destDirectory) throws IOException {
		_destDirectory.mkdirs();
		Enumeration<? extends ZipEntry> entries = _zipFile.entries();
		while ( entries.hasMoreElements() ) {
			ZipEntry entry = entries.nextElement();
			if( entry.isDirectory() ) {
				new File(_destDirectory + File.separator + entry.getName()).mkdirs();
			} else {
				File file = new File(_destDirectory + File.separator + entry.getName());
				file.createNewFile();
				InputStream is = new BufferedInputStream(_zipFile.getInputStream(entry));
				OutputStream os = new FileOutputStream(file);
				copyFile(is, os);
			}
		}
	}
	
	public static void deleteDirectory(File _directoryToDelte) throws FileNotFoundException {
		if ( _directoryToDelte.isDirectory() ) {
			for (File file : _directoryToDelte.listFiles())
			      deleteDirectory(file);
			}
			if (!_directoryToDelte.delete())
				throw new FileNotFoundException("Failed to delete file: " + _directoryToDelte);
	}
	
}
