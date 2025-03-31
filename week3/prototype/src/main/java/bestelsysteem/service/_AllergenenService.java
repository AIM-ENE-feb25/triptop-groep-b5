package bestelsysteem.service;

import java.util.Set;

public interface _AllergenenService {
    Set<VoedingRestrictie> getVoedingRestrictie(String ingredientNaam);
}
