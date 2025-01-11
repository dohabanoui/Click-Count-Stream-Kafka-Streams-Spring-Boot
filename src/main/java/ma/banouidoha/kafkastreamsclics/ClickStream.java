package ma.banouidoha.kafkastreamsclics;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class ClickStream {
    @Bean
    public KStream<String, String> processClicks(StreamsBuilder builder) {
        Consumed<String, String> consumed = Consumed.with(Serdes.String(), Serdes.String());
        Produced<String, Long> produced = Produced.with(Serdes.String(), Serdes.Long());

        KStream<String, String> clicksStream = builder.stream("clicks", consumed);

        clicksStream
                .groupByKey()
                .count(Materialized.as("clicks-count-store"))
                .toStream()
                .to("click-counts", produced);

        return clicksStream;
    }
}

