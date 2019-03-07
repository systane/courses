package job.writers;

import br.com.spring_batch.entities.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvWriter extends FlatFileItemWriter<Person>{

    private static final String CSV_FILE_PAHT = "path.csv.file";

    private final Environment environment;


    @Autowired
    public CsvWriter(Environment environment) {
        this.environment = environment;
        this.lineAggregator = createLineAggregator();
        this.setResource(new FileSystemResource(this.environment.getProperty(CSV_FILE_PAHT)));
        this.setLineAggregator(lineAggregator);
    }

    @Override
    public void write(List<? extends Person> items) throws Exception {
        super.write(items);
    }

    /**
     * This method defines a {@link DelimitedLineAggregator} of an CSV file using
     * the {@link BeanWrapperFieldExtractor} to marks the fields from {@link Person}
     * entity that will be mapped to the CSV file.
     * @return
     */
    private LineAggregator<Person> createLineAggregator() {
        String[] fields = {"account_id", "person_id", "name"};

        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(fields);

        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(" ");
        lineAggregator.setFieldExtractor(fieldExtractor);

        return lineAggregator;
    }
}
