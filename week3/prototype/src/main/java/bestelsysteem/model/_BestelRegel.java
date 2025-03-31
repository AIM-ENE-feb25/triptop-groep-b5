package bestelsysteem.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public record _BestelRegel(AggregateReference<Gerecht, Integer> gerecht, double prijs, int aantal) {
}
