package com.xx.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author xufeng
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result.replaceAll(
				"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
				"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * 
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 生成token,token生成规则: 时间戳+uid+随机码
	 * 
	 * @return 返回token字符串
	 */
	public static String createToken() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(System.currentTimeMillis());
		sbf.append(UUID.randomUUID().toString().replace("-", ""));
		sbf.append(RandomUtils.nextInt(0, 999));
		return sbf.toString();
	}

	/**
	 * 生成32位uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 将文件名称改为uuid作为文件名
	 * @param fileName	原文件名，如abc.html返回{uuid}.html
	 * @return
	 */
	public static String change2UuidFileName(String fileName) {
		String suffix = getExtensionName(fileName);
		return uuid() + "." + suffix;
	}

	/**
	 * 取得文件扩展名
	 * 
	 * @param filename
	 *            文件名，如abc.html
	 * @return 扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	/**
	 * 取得不带前缀的文件名
	 * @param filename	abc.rar
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		int dot = filename.lastIndexOf('.');
		return filename.substring(0, dot);
	}
	
	/**
	 * 判断long型是否为空，0也表示空
	 * @param a	必须非0
	 * @return	true为空，false非空
	 */
	public static boolean isEmpty(Long a) {
		if (a == null || a == 0l) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断List型是否为空
	 * @param a
	 * @return	true为空，false非空
	 */
	public static boolean isEmpty(List a) {
		if (a == null || a.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 字符串插入字符串
	 * @param s	被插入字符串
	 * @param c	新字符
	 * @param i	插入位置
	 * @return
	 */
	public static String insertStr(String s, String c, int i) {
		if (StringUtils.isEmpty(s) || s.length() <= i) {
			return s;
		}
		String newString = s.substring(0, i) + c + s.substring(i, s.length());
		return newString;
	}
	
	/*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray;
		try {
			byteArray = inStr.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
//    /*** 
//     * MD5加密 生成32位md5码
//     * @param 待加密字符串
//     * @return 返回32位md5码
//     */
//    public static String md5Encode(File file) {
//    	// 缓冲区大小（这个可以抽出一个参数）
//        int bufferSize = 256 * 1024;
//        FileInputStream fileInputStream = null;
//        DigestInputStream digestInputStream = null;
//        try {
//           // 拿到一个MD5转换器（同样，这里可以换成SHA1）
//           MessageDigest messageDigest =MessageDigest.getInstance("MD5");
//           // 使用DigestInputStream
//           fileInputStream = new FileInputStream(inputFile);
//           digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
//           // read的过程中进行MD5处理，直到读完文件
//           byte[] buffer =new byte[bufferSize];
//           while (digestInputStream.read(buffer) > 0);
//           // 获取最终的MessageDigest
//           messageDigest= digestInputStream.getMessageDigest();
//           // 拿到结果，也是字节数组，包含16个元素
//           byte[] resultByteArray = messageDigest.digest();
//           // 同样，把字节数组转换成字符串
//           return byteArrayToHex(resultByteArray);
//        } catch (NoSuchAlgorithmException e) {
//           return null;
//        } finally {
//           try {
//              digestInputStream.close();
//           } catch (Exception e) {
//           }
//           try {
//              fileInputStream.close();
//           } catch (Exception e) {
//           }
//        }
//    }
//    
//    /**
//     * //下面这个函数用于将字节数组换成成16进制的字符串
//     * @param b
//     * @return
//     */
//    public static String byteArrayToHex(byte[] b) {
//        String hs = "";   
//        String stmp = "";   
//        for (int n = 0; n < b.length; n++) {   
//            stmp = (Integer.toHexString(b[n] & 0XFF));   
//            if (stmp.length() == 1) {   
//                hs = hs + "0" + stmp;   
//            } else {   
//                hs = hs + stmp;   
//            }   
//            if (n < b.length - 1) {   
//                hs = hs + "";   
//            }   
//        }   
//        return hs;
//    }
	
	public static void main(String[] args) {
		System.out.println(RandomUtils.nextInt(0, 999));
	}
	
	
}
