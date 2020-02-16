import com.isource.query.MapParameter;
import com.isource.query.Query;
import org.junit.Test;


public class QueryTest {

    private void printSQL(Query query){
        System.out.println(String.format("SQL:%s",query.getSql()));

        MapParameter mapParameter = query.getMapParameter();

        for(String item: mapParameter.keySet()){
            System.out.println(String.format("[%s]=%s",item,mapParameter.get(item)));
        }
    }

    @Test
    public void TestQueryObject(){

        Query query = new Query();

        query
                //.select("f1","f2","f3")
                .where()
                .field("f1").equal("1")
                .and()
                .field("f2").like("%2%")
                .orderBy().asc("f1").desc("f2")
                .limit(10);

        printSQL(query);
    }


}
