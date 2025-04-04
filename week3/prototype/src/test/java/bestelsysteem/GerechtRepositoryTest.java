package bestelsysteem.domein;

import bestelsysteem.repository._GerechtRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.Optional;

@DataJdbcTest
public class GerechtRepositoryTest {
    @Autowired
    private _GerechtRepository gerechtRepository;

    @Test
    public void testFindByNaam() {
        //ACT
        Optional<Gerecht> gerecht = gerechtRepository.findByNaam("rib-eye");
        //ASSERT
        Assertions.assertTrue(gerecht.isPresent());
    }

    @Test
    public void testGetMenu() {
        Optional<Menu> menu = gerechtRepository.findMenu(1);
        System.out.println(menu);
    }
}
