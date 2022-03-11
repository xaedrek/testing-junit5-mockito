package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class JunitExtensionMocksTest {
    @Mock
    Map<String, Object> mapMock;



    @Test
    void testMock(){
        mapMock.put("llave","valor");
    }

}
