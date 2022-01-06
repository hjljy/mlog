package cn.hjljy.mlog.common.utils;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * MD解析工具包
 *
 * @author hjljy
 * @date 2021/12/21
 */
public class MarkdownUtils {
    /**
     * 选项 可扩展解析方式
     */
    private static final MutableDataSet OPTIONS = new MutableDataSet();

    private static final Parser PARSER = Parser.builder(OPTIONS).build();

    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    private static final Pattern FRONT_MATTER = Pattern.compile("^(---)?[\\s\\S]*?---");

    private static final Pattern STR_TIME = Pattern.compile("(\\d{4})/(\\d{1,2})/(\\d{1,2})");

    private static final Pattern BLANK_PATTERN = Pattern.compile("\\s");

    private static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

    /**
     * 将MD文件内容解析成html文件内容
     *
     * @param markdown content
     * @return String
     */
    public static String renderHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return StringUtils.EMPTY;
        }
        Node document = PARSER.parse(markdown);
        return RENDERER.render(document);
    }

    /**
     * 将MD文件的头部内容转成map格式
     */

    public static Map<String, Object> getHeader(String markdown) {
        markdown = markdown.trim();
        Matcher matcher = FRONT_MATTER.matcher(markdown);
        if (matcher.find()) {
            markdown = matcher.group();
        }
        Yaml yam = new Yaml();
        return yam.loadAs(markdown, Map.class);
    }

    /**
     * 移除掉头部内容
     *
     * @param markdown md内容
     * @return 移除头部信息后的正文内容
     */
    public static String removeHeader(String markdown) {
        markdown = markdown.trim();
        Matcher matcher = FRONT_MATTER.matcher(markdown);
        if (matcher.find()) {
            return markdown.replace(matcher.group(), "");
        }
        return markdown;
    }

    /**
     * 查找到字符串当中的日期信息  日期格式：yyyy/mm/dd
     *
     * @param str 字符串
     * @return 日期信息 没有则返回null
     */
    public static String findTime(String str) {
        Matcher m = STR_TIME.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 得到网页中图片的地址
     *
     * @param htmlStr html str 网页数据使用字符串传入
     * @return {@link Set}<{@link String}> 图片地址集合
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String regExImg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        Pattern pImage = Pattern.compile
                (regExImg, Pattern.CASE_INSENSITIVE);
        Matcher mImage = pImage.matcher(htmlStr);
        String exImg = "src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";
        while (mImage.find()) {
            // 得到<img />数据
            String img = mImage.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile(exImg).matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
    public static String getFirstImgStr(String htmlStr) {
        String str="";
        String regExImg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        Pattern pImage = Pattern.compile
                (regExImg, Pattern.CASE_INSENSITIVE);
        Matcher mImage = pImage.matcher(htmlStr);
        String exImg = "src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";
        if (mImage.find()) {
            // 得到<img />数据
            String img = mImage.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile(exImg).matcher(img);
            if(m.find()){
                str=m.group(1);
                return str;
            }
        }
        return str;
    }

    /**
     * html格式字数
     *
     * @param htmlContent html内容
     * @return long
     */
    public static int htmlFormatWordCount(String htmlContent) {
        if (htmlContent == null) {
            return 0;
        }
        String cleanContent = cleanHtmlTag(htmlContent);
        Matcher matcher = BLANK_PATTERN.matcher(cleanContent);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return cleanContent.length() - count;
    }

    /**
     * Clean all html tag.
     *
     * @param content html document
     * @return text before cleaned
     */
    public static String cleanHtmlTag(String content) {
        if (StringUtils.isEmpty(content)) {
            return StringUtils.EMPTY;
        }
        return content.replaceAll(RE_HTML_MARK, StringUtils.EMPTY);
    }
}
