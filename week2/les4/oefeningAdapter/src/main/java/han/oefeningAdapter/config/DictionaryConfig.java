package han.oefeningAdapter.config;

import han.oefeningAdapter.adapter.IDictionaryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DictionaryConfig {

    @Bean
    public List<IDictionaryAdapter> dictionaries(List<IDictionaryAdapter> dictionaryAdapters) {
        return dictionaryAdapters;
    }
}