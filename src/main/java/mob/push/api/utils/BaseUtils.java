package mob.push.api.utils;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本工具类
 * Date: 2018/2/5
 * Time: 16:12
 *
 * @author wuchao
 */
public class BaseUtils {

    /**
     * 数组模拟数字的加法
     *
     * @param current 数值
     * @param max     进位最大值
     */
    public static void arrayAdd(int[] current, int[] max) {
        if (current == null || max == null || current.length == 0) {
            throw new IllegalStateException("Arrays can not be null");
        }
        if (current.length != max.length) {
            throw new IllegalStateException("Length must be same");
        }

        int cur = current.length - 1;
        current[cur]++;
        while (cur > 0 && current[cur] >= max[cur]) {
            current[cur] = 0;
            cur--;
            current[cur]++;
        }
    }

    /**
     * 反射获取对象值
     * @param obj 对象
     * @param name 属性名称
     * @return 属性值
     */
    public static Object reflactGet(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        return reflactGet(obj.getClass(), obj, name);
    }

    /**
     * 反射获取属性值
     * @param cls 类
     * @param obj 对象，如果是获取静态熟悉，这个设置null
     * @param name 属性名称
     * @return 属性值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object reflactGet(Class<?> cls, Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        String methodName = nameOfGetMethod(name);
        try {
            Method getMethod = cls.getMethod(methodName);
            return getMethod.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        Field field = cls.getDeclaredField(name);
        boolean isAccess = field.isAccessible();
        Object result;
        if (!isAccess) {
            field.setAccessible(true);
            result = field.get(obj);
            field.setAccessible(isAccess);
        } else {
            result = field.get(obj);
        }
        return result;
    }

    private static String nameOfGetMethod(String name) {
        char begin = toUpper(name.charAt(0));
        return "get" + begin + name.substring(1);
    }

    /**
     * 反射设置值
     * @param obj 对象
     * @param name 对象名称
     * @param value 对象值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void reflectSet(Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        reflectSet(obj.getClass(), obj, name, value);
    }

    /**
     * 反射设置值
     * @param cls 类型
     * @param obj 对象，如果是静态对象，这里传null
     * @param name 属性名称
     * @param value 属性值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void reflectSet(Class<?> cls, Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        // set method first
        String methodName = nameOfSetMethod(name);
        try {
            Method method = cls.getMethod(methodName, value.getClass());
            method.invoke(obj, value);
            return;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }
        // set field if method not exists
        Field field = cls.getDeclaredField(name);
        boolean isAccess = field.isAccessible();
        if (!isAccess) {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(isAccess);
        } else {
            field.set(obj, value);
        }
    }

    private static String nameOfSetMethod(String name) {
        char begin = toUpper(name.charAt(0));
        return "set" + begin + name.substring(1);
    }

    /**
     * 文件读取
     * @param path 路径，文件系统：file:/xx/xx, 项目内：classpath:xxx.properties
     * @return 文件input流
     * @throws FileNotFoundException
     */
    public static InputStream readFile(String path) throws FileNotFoundException {
        if (isEmpty(path)) {
            throw new NullPointerException("Path is empty");
        }
        if (path.startsWith(FILE_PRE)) {
            if (path.length() > FILE_PRE.length()) {
                path =  path.substring(FILE_PRE.length());
                return new FileInputStream(path);
            } else {
                throw new IllegalStateException("Illegal path: " + path);
            }
        } else if (path.startsWith(CLASSPATH_PRE)) {
            if (path.length() > CLASSPATH_PRE.length()) {
                String cp = path.substring(CLASSPATH_PRE.length());
                return BaseUtils.class.getClassLoader().getResourceAsStream(cp);
            } else {
                throw new IllegalStateException("Illegal path: " + path);
            }
        } else if (path.charAt(1) == ':') {
            // C:\a\b
            return new FileInputStream(path);
        }
        return BaseUtils.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * 将文件内容读取为一个字符串列表
     * @param in 输入流
     * @return 字符串列表
     * @throws IOException
     */
    public static List<String> readStreamToList(String in) throws IOException {
        List<String> res = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(readFile(in)))) {
            String line;
            while((line = reader.readLine()) != null) {
                res.add(line);
            }
        }
        return res;
    }

    /**
     * 读取文件，返回一个字符串
     * @param path 路径，文件系统：file:/xx/xx, 项目内：classpath:xxx.properties
     * @return 文件内容字符串
     * @throws IOException
     */
    public static String readFileAsString(String path) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(readFile(path)));
            String result = "";
            String line;
            while ((line = reader.readLine()) != null) {
                line += "\n";
                result += line;
            }
            return result;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static final String FILE_PRE = "file:";
    private static final String CLASSPATH_PRE = "classpath:";

    /**
     * 获取文件绝对路径
     * @param path 路径，文件系统：file:/xx/xx, 项目内：classpath:xxx.properties
     * @return 文件绝对路径
     */
    public static String absFile(String path) {
        if (isEmpty(path)) {
            throw new NullPointerException("Path is empty");
        }
        if (path.startsWith(FILE_PRE)) {
            if (path.length() > FILE_PRE.length()) {
                return path.substring(FILE_PRE.length());
            } else {
                throw new IllegalStateException("Illegal path: " + path);
            }
        } else if (path.startsWith(CLASSPATH_PRE)) {
            if (path.length() > CLASSPATH_PRE.length()) {
                String cp = path.substring(CLASSPATH_PRE.length());
                URL url = BaseUtils.class.getClassLoader().getResource(cp);
                return url.getFile();
            } else {
                throw new IllegalStateException("Illegal path: " + path);
            }
        }
        return path;
    }

    /**
     * 判断字符串是否是数字
     * @param str 字符串
     * @return 是否数字
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 生成md5
     *
     * @param message 内容
     * @return md5
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            //1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            //2 将消息变成byte数组
            byte[] input = message.getBytes();

            //3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            //4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes 二进制数据
     * @return 十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder md5str = new StringBuilder();
        //把数组每一字节换成16进制连成md5字符串
        int digital;
        for (byte aByte : bytes) {
            digital = aByte;

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }

    /**
     * 获取当前时间到明天的毫秒数
     */
    public static long millisToTomorrow() {
        long current = System.currentTimeMillis();// 当前时间毫秒数
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowzero = calendar.getTimeInMillis();
        return tomorrowzero - current;
    }

    public static String extractByPattern(String base, String ptn, int index) {
        Pattern pattern = Pattern.compile(ptn);
        return extractByPattern(base, pattern, index);
    }
    public static String extractByPattern(String base, Pattern ptn, int index) {
        Matcher mth = ptn.matcher(base);
        if (mth.find()) {
            return mth.group(index);
        } else {
            return null;
        }
    }

    /**
     * 数组中是否包含某个对象
     * @param vs 数组
     * @param v 对象
     */
    public static <T> boolean contains(T[] vs, T v) {
        for (T vv : vs) {
            if (v.equals(vv)) {
                return true;
            }
        }
        return false;
    }

    private static final String SPLIT_PTN = "[|;,\t]";

    /**
     * 常用字符串切分，基于 "[|;,\t]"
     * @param source 字符串
     */
    public static String[] split(String source) {
        return split(source, SPLIT_PTN);
    }

    public static final String UNICODE_0001 = "\u0001";
    /**
     * 常用字符串切分，基于 "\u0001"
     * @param source 字符串
     */
    public static String[] splitsByUnicode(String source) {
        return split(source, UNICODE_0001);
    }
    public static final String TAB = "\t";
    /**
     * 常用字符串切分，基于 "\t"
     * @param source 字符串
     */
    public static String[] splitByTab(String source) {
        return split(source, TAB);
    }

    /**
     * 常用字符串切分，ptn
     * @param source 字符串
     * @param ptn 正则
     */
    public static String[] split(String source, String ptn) {
        return source.split(ptn);
    }

    /**
     * 获取文件名称
     * @param path 文件路径
     */
    public static String splitFileName(String path) {
        path = absFile(path);
        int end = path.lastIndexOf("/");
        if (end < 0) {
            end = path.lastIndexOf("\\");
        }
        return path.substring(end + 1);
    }

    /**
     * 获取文件目录绝对路径
     * @param path 文件路径
     */
    public static String dir(String path) {
        path = absFile(path);
        int end = path.lastIndexOf("/");
        if (end < 0) {
            end = path.lastIndexOf("\\");
        }
        return path.substring(0, end + 1);
    }

    public static int indexOfIgnoreCase(String base, String target) {
        for (int i = 0, len = base.length() - target.length(); i < len; i++) {
            for (int j = 0; j < target.length(); j++) {
                char b = base.charAt(i + j);
                char t = target.charAt(j);
                b = toLower(b);
                t = toLower(t);
                if (b != t) {
                    break;
                }
                if (j == target.length() - 2) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static char toLower(char c) {
        if ('A' <= c && c <= 'Z') {
            c = (char) (c - 'A' + 'a');
        }
        return c;
    }
    private static char toUpper(char c) {
        if ('a' <= c && c <= 'z') {
            c = (char) (c - 'a' + 'A');
        }
        return c;
    }

    /**
     * 新建一个简单map
     */
    public static <K, V> Map<K,V> newMap(K key, V value) {
        Map<K, V> res = new HashMap<>();
        res.put(key, value);
        return res;
    }

    /**
     * 将流写入指定路径
     * @param input 流
     * @param targetFile 目标文件
     */
    public static void transfer(InputStream input, String targetFile) throws IOException {
        File dir = new File(targetFile).getParentFile();
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        try(FileOutputStream fos = new FileOutputStream(targetFile)) {
            byte[] buf = new byte[4096];
            int len;
            while ((len = input.read(buf)) > -1) {
                fos.write(buf, 0, len);
            }
        }
    }

    /**
     * 将二进制写入指定路径
     * @param input 二进制
     * @param targetFile 目标文件
     */
    public static void transfer(byte[] input, String targetFile) throws IOException {
        File dir = new File(targetFile).getParentFile();
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        try(FileOutputStream fos = new FileOutputStream(targetFile)) {
            fos.write(input);
        }
    }

    /**
     * 连接字符串
     * @param args 对象列表
     * @param sep 分隔符
     */
    public static String join(Collection<String> args, String sep) {
        if (args.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(sep);
        }
        sb.delete(sb.length() - sep.length(), sb.length());
        return sb.toString();
    }

    /**
     * 等到到指定时间
     * @param date 指定时间
     */
    public static void waitTo(long date) throws InterruptedException {
        long now = System.currentTimeMillis();
        long wait = date - now;
        if (wait > 0) {
            Thread.sleep(wait);
        }
    }

    /**
     * 是否全部为空
     * @param args 参数列表
     */
    public static boolean isAllEmpty(Object... args) {
        for (Object arg : args) {
            if (!isEmpty(arg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否包含空对象
     * @param args 参数列表
     */
    public static boolean containsEmpty(Object... args) {
        for (Object arg : args) {
            if (isEmpty(arg)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(Object target) {
        if (target == null) {
            return true;
        }
        if (target instanceof String && target.equals("")) {
            return true;
        } else if (target instanceof Collection) {
            return ((Collection<?>) target).isEmpty();
        } else if (target instanceof Map) {
            return ((Map<?, ?>) target).isEmpty();
        } else if (target.getClass().isArray()) {
            return Array.getLength(target) == 0;
        }
        return false;
    }

    /**
     * 驼峰名称转下划线名称, e: userName -> user_name
     * @param str 名称
     */
    public static String toUnderline(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('A' <= c && c <= 'Z') {
                if (i > 0) {
                    res.append('_');
                }
                res.append((char) (c - 'A' + 'a'));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static final char[] ITEMS = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 转换32进制字符串
     * @param number 数字
     */
    public static String converse32Hex(long number) {
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int index = (int) (number & 0x1F);
            result.insert(0, ITEMS[index]);
            number = (number >>> 5);
        }
        return result.toString();
    }

    private static final String[] SIZE_UNIT = new String[]{"B", "KB", "MB", "GB"};
    private static final String SIZE_FORMAT = "%.2f%s";

    /**
     * 转换人类可读存储单位
     * @param size size
     */
    public static String sizeHuman(double size) {
        for (String aSIZE_UNIT : SIZE_UNIT) {
            double next = size / 1024;
            if (next < 1) {
                return String.format(SIZE_FORMAT, size, aSIZE_UNIT);
            } else {
                size = next;
            }
        }
        return String.format(SIZE_FORMAT, size, "GB");
    }

    /**
     * 对象转数组
     * @param src 对象
     */
    public static Object[] toArray(Object src) {
        if (src.getClass().isArray()) {
            Object[] res = new Object[Array.getLength(src)];
            System.arraycopy(src, 0, res, 0, res.length);
            return res;
        } else if (Collection.class.isInstance(src)) {
            Collection<?> c = (Collection<?>) src;
            return c.toArray();
        } else {
            return new Object[]{src};
        }
    }

    /**
     * 文件处理
     * @param paths 文件路径数组
     * @param filter 处理器
     */
    public static void reader(String[] paths, Filter filter) throws Exception {
        reader(paths, filter, 1);
    }

    /**
     * 文件处理
     * @param paths 文件数组
     * @param filter 处理器
     * @param core 多线程处理核心数
     */
    public static void reader(String[] paths, Filter filter, int core) throws Exception {
        for (String path: paths) {
            reader(path, filter, core);
        }
    }

    private static ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd-HHmmss"));
    public static String currentTimeStr() {
        return dateFormat.get().format(System.currentTimeMillis());
    }

    public interface Filter {
        boolean filter(String line) throws Exception;
    }
    public static void reader(String path, Filter filter) throws Exception {
        reader(readFile(path), filter, 1);
    }

    private static class MultiFilter implements Filter, AutoCloseable {
        private Filter filter;
        private int core;
        private ExecutorService executor;
        private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        public MultiFilter(Filter filter, int core) {
            this.filter = filter;
            this.core = core;
            this.executor = Executors.newFixedThreadPool(this.core);
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String line = queue.take();
                            if (!MultiFilter.this.filter.filter(line)) {
                                return;
                            }
                        } catch (Exception ignored) {}
                    }
                }
            };
            for (int i= 0; i < this.core; i++) {
                executor.execute(worker);
            }
        }
        @Override
        public boolean filter(final String line) throws Exception {
            queue.put(line);
            return true;
        }

        @Override
        public void close() throws Exception {
            Thread.sleep(5000);
            executor.shutdown();
        }
    }

    /**
     * 多线程处理流内容
     * @param in 输入流
     * @param filter 处理器
     * @param core 核心数
     */
    public static void reader(InputStream in, Filter filter, int core) throws Exception {
        if (core > 1) {
            filter = new MultiFilter(filter, core);
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!filter.filter(line)) {
                    return;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (core > 1) {
                ((MultiFilter) filter).close();
            }
        }
    }

    /**
     * 文件处理
     * @param path 文件
     * @param filter 处理器
     * @param core 多线程处理核心数
     */
    public static void reader(String path, Filter filter, int core) throws Exception {
        reader(readFile(path), filter, core);
    }

    private static String[] timeUnits = new String[]{"秒", "分", "小时", "天"};

    /**
     * 秒数转换为人类可读的字符串
     * @param seconds 秒数
     */
    public static String showTime(int seconds) {
        if (seconds < 60) {
            return String.format("%s秒", seconds);
        }
        seconds /= 60;
        if (seconds < 60) {
            return String.format("%s分", seconds);
        }
        int remain = seconds % 60;
        seconds /= 60;
        if (seconds < 24) {
            if (remain > 0) {
                return String.format("%s小时%s分", seconds, remain);
            } else {
                return String.format("%s小时", seconds);
            }
        }
        remain = seconds % 24;
        seconds /= 24;
        if (remain > 0) {
            return String.format("%s天%s小时", seconds, remain);
        } else {
            return String.format("%s天", seconds);
        }
    }

    /**
     * 获取字符串16进制后缀
     * @param value v
     */
    public static String suffix(String value) {
        return suffix(value, 1);
    }

    public static String suffix(String value, int size) {
        BigInteger mod = new BigInteger(value, 16);
        mod = mod.mod(BigInteger.valueOf((1 << size) - 1));
        return Integer.toHexString(mod.intValue());
    }

    public static Integer toInt(String text) {
        try {
            return Integer.valueOf(text);
        }catch (Exception e) {
            return 0;
        }
    }

    public static boolean phoneCheck(String phone) {
        if (isEmpty(phone)) return false;
        String pattern = "1[1-9]\\d{9}";
        return Pattern.matches(pattern, phone);
    }
}