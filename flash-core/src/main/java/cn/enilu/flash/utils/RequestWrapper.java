package cn.enilu.flash.utils;

import org.apache.xmlbeans.impl.common.IOUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @Description: 对HttpServletRequest进行重写，
 * 1、用来接收application/json参数数据类型，即@RequestBody注解标注的参数,解决多次读取问题
 * 2、用来解决注解@RequestParam通过POST/PUT/DELETE/PATCH方法传递参数，解决多次读取问题
 * 首先看一下springboot控制器三个注解：
 * 1、@PathVariable注解是REST风格url获取参数的方式，只能用在GET请求类型，通过getParameter获取参数
 * 2、@RequestParam注解支持GET和POST/PUT/DELETE/PATCH方式，Get方式通过getParameter获取参数和post方式通过getInputStream或getReader获取参数
 * 3、@RequestBody注解支持POST/PUT/DELETE/PATCH，可以通过getInputStream和getReader获取参数
 * @create: 2020/8/19
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    //参数字节数组
    private byte[] requestBody;
    //Http请求对象
    private HttpServletRequest request;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.request = request;
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        /**
         * 每次调用此方法时将数据流中的数据读取出来，然后再回填到InputStream之中
         * 解决通过@RequestBody和@RequestParam（POST方式）读取一次后控制器拿不到参数问题
         */
        if (null == this.requestBody) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtil.copyCompletely(request.getInputStream(), baos);
            this.requestBody = baos.toByteArray();
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }

    public byte[] getRequestBody() {
        return requestBody;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}