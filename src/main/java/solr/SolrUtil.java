package solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

/**
 * solr工具类
 */
public class SolrUtil {
    private static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/articles";
        client = new HttpSolrClient.Builder(url).build();
    }

    /**
     * 保存或者更新solr数据
     * 
     * @param res
     */
    public static <T> boolean saveSolrResource(T solrEntity) {

        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(solrEntity);
        try {
            client.add(doc);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除指定数据
     * 
     * @param id
     */
    public static boolean removeSolrData(String id) {
        try {
            client.deleteById(id);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 删除所有数据
     * 
     * @param id
     */
    public static boolean removeAllData() {
        try {
        	client.deleteByQuery("*:*");
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     /**
     * 查询
     * 
     * @param keywords
     */
    public static QueryResponse query(String keywords) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery(keywords);
        QueryResponse rsp = client.query(query);
        return rsp;
    }

    
    public static void queryHighlight(String keywords) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("name:" + keywords + "or content:" + keywords); // 设置查询关键字
        solrQuery.setHighlight(true); // 开启高亮
        solrQuery.addHighlightField("name"); // 高亮字段
        solrQuery.addHighlightField("content"); // 高亮字段
        solrQuery.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
        solrQuery.setHighlightSimplePost("</font>"); // 高亮单词的后缀
        /**
         * hl.snippets
         * hl.snippets参数是返回高亮摘要的段数，因为我们的文本一般都比较长，含有搜索关键字的地方有多处，如果hl.snippets的值大于1的话，
         * 会返回多个摘要信息，即文本中含有关键字的几段话，默认值为1，返回含关键字最多的一段描述。solr会对多个段进行排序。
         * hl.fragsize
         * hl.fragsize参数是摘要信息的长度。默认值是100，这个长度是出现关键字的位置向前移6个字符，再往后100个字符，取这一段文本。
         */
        solrQuery.setHighlightFragsize(15);

        QueryResponse query = client.query(solrQuery);
        SolrDocumentList results = query.getResults();
        NamedList<Object> response = query.getResponse();
        NamedList<?> highlighting = (NamedList<?>) response.get("highlighting");
        for (int i = 0; i < highlighting.size(); i++) {
            System.out.println(highlighting.getName(i) + "：" + highlighting.getVal(i));
        }
        for (SolrDocument result : results) {
            System.out.println(result.toString());
        }
    }
}
