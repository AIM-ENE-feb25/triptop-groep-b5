package bestelsysteem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class _Bestelling {
    private @Id @JsonIgnore Integer id;
    private Integer bestelnummer;
    private _BestelStatus status;
    private Set<_BestelRegel> gerechten;

    // vereist voor Spring Data JDBC om bestaande bestellingen te kunnen reconstrueren vanuit de repository interface
    // TODO: is er ook een manier zonder NO_CONSTRUCTOR?
    protected _Bestelling() {
    }

    public _Bestelling(Integer bestelnummer, _BestelStatus status, Set<_BestelRegel> gerechten) {
        this.id = null; // triggers an insert query
        this.bestelnummer = bestelnummer;
        this.status = status;
        this.gerechten = gerechten;
    }

    public _Bestelling(Integer bestelnummer, List<Gerecht> gerechten) {
        this(bestelnummer, _BestelStatus.OPEN, maakBestelregels(gerechten)); /* 1 betekent nieuwe bestelling */
    }

    private static Set<_BestelRegel> maakBestelregels(List<Gerecht> gerechten) {
        Map<Integer, Long> collectOccurance = gerechten.stream().collect(Collectors.groupingBy(Gerecht::id, Collectors.counting()));
        return gerechten.stream().map(gerecht ->
                new _BestelRegel(AggregateReference.to(gerecht.id()),
                        gerecht.prijs(), Math.toIntExact(collectOccurance.get(gerecht.id())))
        ).collect(Collectors.toSet());
    }

    public Integer getBestelnummer() {
        return bestelnummer;
    }

    public _BestelStatus getStatus() {
        return status;
    }

    public double getSubTotaal() {
        return gerechten.stream().map(bestelRegel -> bestelRegel.prijs() * bestelRegel.aantal())
                .reduce(0.0, Double::sum);
    }

    public Set<_BestelRegel> getGerechten() {
        return Collections.unmodifiableSet(gerechten);
    }
}
