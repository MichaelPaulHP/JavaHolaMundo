

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import com.mongodb.hadoop.io.BSONWritable;
import com.mongodb.hadoop.util.MapredMongoConfigUtil;
import com.mongodb.hadoop.util.MongoConfigUtil;
import com.mongodb.hadoop.util.MongoTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ToolRunner;

import java.net.UnknownHostException;

public class HolaMundo extends MongoTool {
	
	public HolaMundo() throws UnknownHostException {
        setConf(new Configuration());

        if (MongoTool.isMapRedV1()) {
            MapredMongoConfigUtil.setInputFormat(getConf(), com.mongodb.hadoop.mapred.MongoInputFormat.class);
            MapredMongoConfigUtil.setOutputFormat(getConf(), com.mongodb.hadoop.mapred.MongoOutputFormat.class);
        } else {
            MongoConfigUtil.setInputFormat(getConf(), MongoInputFormat.class);
            MongoConfigUtil.setOutputFormat(getConf(), MongoOutputFormat.class);
        }
        
        MongoConfigUtil.setInputURI(getConf(), "mongodb://MichaelHP:michael123@ds129776.mlab.com:29776/home.products");
        MongoConfigUtil.setOutputURI(getConf(), "mongodb://MichaelHP:michael123@ds129776.mlab.com:29776/home.results");
        
        
        
        MongoConfigUtil.setMapper(getConf(), ProductMapper.class);
        MongoConfigUtil.setReducer(getConf(), ProductReducer.class);
        MongoConfigUtil.setMapperOutputKey(getConf(), Text.class);
        MongoConfigUtil.setMapperOutputValue(getConf(), Text.class);
        MongoConfigUtil.setOutputKey(getConf(), IntWritable.class);
        MongoConfigUtil.setOutputValue(getConf(), BSONWritable.class);
        
      
    }
	public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new HolaMundo(), args));

	}

}
