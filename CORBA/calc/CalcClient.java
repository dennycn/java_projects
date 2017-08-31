import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import zzvcom.cbrtest.Calc;
import zzvcom.cbrtest.CalcHelper;

/**
 * 四则运算的客户端调用
 *
 * @author leizhimin 2009-12-17 10:35:32
 */
public class CalcClient {
	static Calc calcImpl;

	public static void main(String args[]) {
		try {
			// 创建一个ORB实例
			ORB orb = ORB.init(args, null);

			// 获取根名称上下文
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// 从命名上下文中获取接口实现对象
			String name = "Calc";
			calcImpl = CalcHelper.narrow(ncRef.resolve_str(name));

			// 调用接口对象的方法
			System.out.println("Obtained a handle on server object: " + calcImpl);
			System.out.println();
			IntHolder add_val = new IntHolder();
			calcImpl.add(1, 2, add_val);
			System.out.println(add_val.value);
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}