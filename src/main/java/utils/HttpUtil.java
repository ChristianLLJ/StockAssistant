package utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author 专治八阿哥的孟老师
 */
public class HttpUtil {


    public static String httpPost(String urlStr, String params, String charSet) {
        HttpURLConnection httpConn = null;
        try {
            byte[] data = params.getBytes(charSet);
            URL url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Content-Length", String.valueOf(data.length));
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//jdk1.4换成这个,连接超时
            // System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //jdk1.4换成这个,读操作超时
            httpConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpConn.connect();
            OutputStream os1 = httpConn.getOutputStream();
            os1.write(data);
            os1.flush();
            os1.close();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
        } finally {
            if (null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }


    public static String httpsPost(String urlStr, String params, String charSet) {
        HttpsURLConnection httpsConn = null;
        try {
            byte[] data = params.getBytes(charSet);
            URL url = new URL(urlStr);
            HttpsHandler.trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.setRequestMethod("POST");
            httpsConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpsConn.setRequestProperty("Content-Length", String.valueOf(data.length));
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpsConn.connect();
            OutputStream os1 = httpsConn.getOutputStream();
            os1.write(data);
            os1.flush();
            os1.close();
            return getResponseResult(httpsConn, urlStr, charSet);
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
        } finally {
            if (null != httpsConn) {
                httpsConn.disconnect();
            }
        }
        return null;
    }

    public static String httpPostJson(String urlStr, String params, String charSet) {
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) ((new URL(urlStr).openConnection()));
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            byte[] outputBytes = params.getBytes(charSet);
            OutputStream os = httpConn.getOutputStream();
            os.write(outputBytes);
            os.close();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }

    /**
     * 发送json字符
     *
     * @param urlStr
     * @param params
     * @param charSet
     * @param header
     * @param headerVal
     * @return
     */
    public static String httpPostJson(String urlStr, String params, String charSet, String header, String headerVal) {
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) ((new URL(urlStr).openConnection()));
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            if (header != null && header.trim().length() > 0) {
                httpConn.setRequestProperty(header, headerVal);
            }
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            byte[] outputBytes = params.getBytes(charSet);
            OutputStream os = httpConn.getOutputStream();
            os.write(outputBytes);
            os.close();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }


    public static String httpGet(String urlStr, String params, String charSet) {
        HttpURLConnection httpConn = null;
        try {
            if (null != params && params.length() > 0) {
                if (urlStr.indexOf("?") == -1) {
                    urlStr += "?" + params;
                } else {
                    urlStr += "&" + params;
                }
            }
            URL url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//jdk1.4换成这个,连接超时
            // System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //jdk1.4换成这个,读操作超时
            httpConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpConn.connect();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
            ex.printStackTrace();
        } finally {
            if (null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }

    public static String httpsGet(String urlStr, String params, String charSet) {
        HttpsURLConnection httpsConn = null;
        try {
            if (null != params && params.length() > 0) {
                if (urlStr.indexOf("?") == -1) {
                    urlStr += "?" + params;
                } else {
                    urlStr += "&" + params;
                }
            }
            byte[] data = params.getBytes(charSet);
            URL url = new URL(urlStr);
            HttpsHandler.trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.setRequestMethod("GET");
            httpsConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpsConn.setRequestProperty("Content-Length", String.valueOf(data.length));
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpsConn.connect();
            OutputStream os1 = httpsConn.getOutputStream();
            os1.write(data);
            os1.flush();
            os1.close();
            return getResponseResult(httpsConn, urlStr, charSet);
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
            ex.printStackTrace();
        } finally {
            if (null != httpsConn) {
                httpsConn.disconnect();
            }
        }
        return null;
    }

    public static byte[] httpsGetMedia(String urlStr, String params, String charSet) {
        HttpsURLConnection httpsConn = null;
        try {
            if (null != params && params.length() > 0) {
                if (urlStr.indexOf("?") == -1) {
                    urlStr += "?" + params;
                } else {
                    urlStr += "&" + params;
                }
            }
            byte[] data = params.getBytes(charSet);
            URL url = new URL(urlStr);
            HttpsHandler.trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.setRequestMethod("GET");
            httpsConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpsConn.setRequestProperty("Content-Length", String.valueOf(data.length));
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpsConn.connect();
            OutputStream os1 = httpsConn.getOutputStream();
            os1.write(data);
            os1.flush();
            os1.close();
            int responseCode = httpsConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                System.out.println(httpsConn.getContentType());
                byte[] buffer = new byte[1024];
                int len = -1;
                InputStream is = httpsConn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                is.close();
                bos.close();
                return bos.toByteArray();
            } else {
                System.out.println(urlStr + " Response Code:" + responseCode);
            }
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
            ex.printStackTrace();
        } finally {
            if (null != httpsConn) {
                httpsConn.disconnect();
            }
        }
        return null;
    }

    public static String httpsPostMedia(String urlStr, File file, String charSet) {
        HttpsURLConnection httpsConn = null;
        try {
            URL url = new URL(urlStr);
            HttpsHandler.trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.setRequestMethod("POST");
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            httpsConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpsConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpsConn.setUseCaches(false);
            httpsConn.setRequestProperty("connection", "Keep-Alive");
//            httpsConn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            httpsConn.setRequestProperty("Charset", charSet);
            httpsConn.setRequestProperty("Content-Type", "multipart/form-data");
            httpsConn.connect();
            OutputStream os1 = httpsConn.getOutputStream();
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
            byte[] END_DATA = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media"
                    + "\";filename=\"" + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] data = sb.toString().getBytes();
            os1.write(data);

            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                os1.write(bufferOut, 0, bytes);
            }
            os1.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
            os1.write(END_DATA);
            in.close();
            os1.flush();
            os1.close();
            return getResponseResult(httpsConn, urlStr, charSet);
        } catch (Exception ex) {
            if (ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
            ex.printStackTrace();
        } finally {
            if (null != httpsConn) {
                httpsConn.disconnect();
            }
        }
        return null;
    }

    private static String getResponseResult(HttpURLConnection httpConn, String urlStr, String charSet) throws IOException {
        String res = null;
        // 获得响应状态
        int responseCode = httpConn.getResponseCode();
        if (HttpURLConnection.HTTP_OK == responseCode) {
            byte[] buffer = new byte[1024];
            int len = -1;
            InputStream is = httpConn.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            res = bos.toString(charSet);
            is.close();
            bos.close();
            System.out.println(urlStr + " Response Code:" + responseCode + " content:" + res);
        } else {
            System.out.println(urlStr + " Response Code:" + responseCode);
        }
        return res;
    }

}
