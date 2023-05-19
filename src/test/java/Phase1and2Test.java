//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.sparta.ac.jdbc.EmployeeDept;
//import com.sparta.ac.jdbc.Phase1and2;
//import org.junit.jupiter.api.Test;
//import java.io.IOException;
//import java.util.ArrayList;
//import static org.mockito.Mockito.*;
//
//public class Phase1and2Test {
//    @Test
//    public void testJacksonJSONXML() throws IOException {
//        ArrayList<EmployeeDept> employeesDept = new ArrayList<>();
//        employeesDept.add(new EmployeeDept(1, "D001", "Development", "2022-01-01", "2022-12-31"));
//
//        ObjectMapper mapper = mock(ObjectMapper.class);
//        when(mapper.writeValueAsString(employeesDept)).thenReturn("json_data");
//
//        XmlMapper xmlMapper = mock(XmlMapper.class);
//        when(xmlMapper.writeValueAsString(employeesDept)).thenReturn("xml_data");
//
//        Phase1and2.jacksonJSONXML("filename", employeesDept);
//
//        verify(mapper).writeValueAsString(employeesDept);
//        verify(xmlMapper).writeValueAsString(employeesDept);
//    }
//
//}
