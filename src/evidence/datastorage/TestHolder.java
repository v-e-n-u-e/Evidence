package evidence.datastorage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "TestList")
public class TestHolder {

	private List<TestClass> listTestClass = new ArrayList<TestClass>();

	@XmlElement(name = "TestClass") // makes <TestClass> instead of <listTestClass>
	public List<TestClass> getListTestClass() {
		return listTestClass;
	}

	public void setListTestClass(List<TestClass> listTestClass) {
		this.listTestClass = listTestClass;
	}
	
}
