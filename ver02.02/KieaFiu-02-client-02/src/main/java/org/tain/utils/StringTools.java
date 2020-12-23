package org.tain.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class StringTools {

	// text.replaceAll("(?<=^.{47}).*$", "...");

	public static String truncate(String line, int maxLength) {
		if (line.length() < maxLength)
			return line;
		int pos = line.lastIndexOf(" ", maxLength - 3);
		if (pos <= 0)
			pos = maxLength - 3; // no spaces, so just cut anyway
		return line.substring(0, pos) + "...";
	}

	public static String smartSubstring(String str, int maxLength) {
		String subStr = str.substring(0);
		if (maxLength == 0) {
			return "";
		} else if (str.length() <= maxLength) {
			return str;
		} else {
			int i = maxLength;
			while (i >= 0) {
				while (str.length() < i) {
					i--;
				}
				if (str.charAt(i) == ' ') {
					subStr = str.substring(0, i);
					break;
				}
				i--;
			}
			return subStr;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private final static String NON_THIN = "[^iIl1\\.,']";

	private static int textWidth(String str) {
		return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
	}

	public static String ellipsize(String text, int max) {

		if (textWidth(text) <= max)
			return text;

		// Start by chopping off at the word before max
		// This is an over-approximation due to thin-characters...
		int end = text.lastIndexOf(' ', max - 3);

		// Just one long word. Chop it off.
		if (end == -1)
			return text.substring(0, max - 3) + "...";

		// Step forward as long as textWidth allows.
		int newEnd = end;
		do {
			end = newEnd;
			newEnd = text.indexOf(' ', end + 1);

			// No more spaces.
			if (newEnd == -1)
				newEnd = text.length();

		} while (textWidth(text.substring(0, newEnd) + "...") < max);

		return text.substring(0, end) + "...";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// KANG-20200918
	public static String stringFromFile(String filePath) {
		StringBuffer sb = new StringBuffer();
		if (Flag.flag) {
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(filePath));
				String line = null;
				while ((line = br.readLine()) != null) {
					if ("".equals(line.trim()) || line.trim().charAt(0) == '#')
						continue;
					sb.append(line).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) try { br.close(); } catch (Exception e) {}
			}
		}
		return sb.toString();
	}
	
	// KANG-20200918
	public static void stringToFile(String strSource, String filePath) {
		if (Flag.flag) {
			PrintWriter pw = null;
			
			try {
				pw = new PrintWriter(new FileWriter(filePath));
				pw.print(strSource);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) try { pw.close(); } catch (Exception e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// KANG-20200918
	public static byte[] byteFromFile(String filePath) {
		byte[] bRet = null;
		if (Flag.flag) {
			try {
				//bRet = Files.readAllBytes(new File(filePath).toPath());
				bRet = Files.readAllBytes(Paths.get(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return bRet;
	}
	
	// KANG-20200918
	public static void byteToFile(byte[] bSource, String filePath) {
		if (Flag.flag) {
			try {
				Files.write(Paths.get(filePath), bSource);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static byte[] getBytesFromString(String hexString) {
		if (Flag.flag) {
			return DatatypeConverter.parseHexBinary(hexString);
		}
		int len = hexString.length();
		byte[] bRet = new byte[len / 2];
		for (int i=0; i < len; i += 2) {
			bRet[i/2] = (byte)((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i+1), 16));
		}
		return bRet;
	}
	
	public static String getStringFromBytes(byte[] bytes) {
		if (!Flag.flag) {
			return DatatypeConverter.printHexBinary(bytes);
		}
		
		StringBuilder sb = new StringBuilder();
		for (byte byt : bytes) {
			sb.append(String.format("%02X", byt & 0xff));
		}
		return sb.toString();
	}
	
	public static String byteToHex(byte byt) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((byt >> 4) & 0xF, 16);
		hexDigits[1] = Character.forDigit(byt & 0xF, 16);
		return new String(hexDigits);
	}
	
	public static String getByteString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte byt : bytes) {
			sb.append(String.format(" %02X", byt & 0xff));
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(0);
		}
		
		return sb.toString();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static String splitLine(String string, int size) {
		StringBuffer sb = new StringBuffer();
		
		for (int offset = 0; offset < string.length(); offset += size) {
			int offsetEnd = Math.min(offset + size, string.length());
			sb.append(string.substring(offset, offsetEnd));
			sb.append('\n');
		}
		return sb.toString();
	}
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getExtension(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0)
			return "_NO_EXT_";
		
		return fileName.substring(pos + 1);
	}
	
	public static boolean isExtension(String fileName, String ext) {
		return getExtension(fileName).equals(ext);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getDateTime(String strFormat) throws Exception {
		return new SimpleDateFormat(strFormat).format(new Date());
	}
	
	public static String getYYYY() throws Exception {
		return getDateTime("yyyy");
	}
	
	public static String getYYMMDD() throws Exception {
		return getDateTime("yyMMdd");
	}
	
	public static String getYYYYMMDD() throws Exception {
		return getDateTime("yyyyMMdd");
	}
	
	public static String getYYMMDDHHMMSS() throws Exception {
		return getDateTime("yyMMddHHmmss");
	}
	
	public static String getYYYYMMDDHHMMSS() throws Exception {
		return getDateTime("yyyyMMddHHmmss");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getDashLine(char ch) {
		if (ch == '-') {
			return "-----------------------------------------------------------";
		} else {
			return "===========================================================";
		}
	}
}
