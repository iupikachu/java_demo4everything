package Jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileInputStream;
import java.io.PrintStream;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Student.java
 * @Description TODO
 * @createTime 2021年09月01日 10:23:00
 */
@XmlRootElement
public class Student {
    private String id;

    private String name;

    private Integer age;

    public Student() {}

    public Student(String id, String name, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }


    public static void javaToxml(Student stu) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Student
        JAXBContext context = JAXBContext.newInstance(Student.class);
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // 构建输出环境 -> 这里使用标准输出，输出到控制台Console
        PrintStream out = System.out;
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(stu, out);
    }

    public static void xmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Student
        JAXBContext context = JAXBContext.newInstance(Student.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        //InputStream stream = SimpleTsxt.class.getClassLoader().getResourceAsStream("lesson1.xml");

        FileInputStream stream = new FileInputStream("/Users/chenqipeng/programming/code/Demo4Everything/src/main/java/Jaxb/xml/lesson1.xml");
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Student stu = (Student) unmarshaller.unmarshal(stream);
        // 将结果打印到控制台
        System.out.println(stu);
    }

    public static void main(String[] args) throws Exception {
        Student stu = new Student("001","Tom",22);
        javaToxml(stu);
        xmlTojava();
    }
}
