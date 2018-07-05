import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;
import com.mongodb.hadoop.io.BSONWritable;
import org.bson.BSONObject;



public class ProductMapper extends Mapper<Object, BSONObject, Text, Text>
implements org.apache.hadoop.mapred.Mapper<Object, BSONWritable, Text, Text> 
{
	
	 private final Text keyText;
	 private final Text valueText;
	
	 public ProductMapper() {
	        super();
	        keyText = new Text();
	        valueText = new Text();
	        
	    }

	@Override
	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void map(final Object key, final BSONObject val, final Context context) throws IOException, InterruptedException {
        String keyOut = (String) val.get("categories");
        keyText.set(keyOut);
        valueText.set(val.get("_id").toString());
        context.write(keyText, valueText);
    }

    @Override
    public void map(final Object key, final BSONWritable value, final OutputCollector<Text, Text> output,
                    final Reporter reporter) throws IOException {
        BSONObject val = value.getDoc();
        
        String keyOut = (String) val.get("categories");
        keyText.set(keyOut);
        valueText.set(val.get("_id").toString());
        output.collect(keyText, valueText);
    }

}

