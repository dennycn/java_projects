import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import zzvcom.cbrtest.Calc;
import zzvcom.cbrtest.CalcHelper;
import zzvcom.cbrtest.CalcPOA;

/**
 * 四则运算的服务端实现
 *
 * @author leizhimin 2009-12-17 9:54:43
 */
class CalcImpl extends CalcPOA {
	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	public void add(int a, int b, IntHolder c) {
		c.value = a + b;
	}

	public void sub(int a, int b, IntHolder c) {
		c.value = a - b;
	}

	public void muti(int a, int b, IntHolder c) {
		c = new IntHolder(a * b);
	}

	public void div(int a, int b, IntHolder c) {
		c = new IntHolder(a / b);
	}

	public void shutdown() {
		orb.shutdown(false);
	}
}

public class CalcServer {

	public static void main(String args[]) {
		try {
			// 创建一个ORB实例
			ORB orb = ORB.init(args, null);

			// 得到一个RootPOA的引用，并激活POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// 创建一个CalcImpl实例（servant），并注册到ORB上
			CalcImpl calcImpl = new CalcImpl();
			calcImpl.setORB(orb);

			// 从服务中得到对象的引用
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calcImpl);
			Calc href = CalcHelper.narrow(ref);

			// 得到一个根名称的上下文
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// 在命名上下文中绑定这个对象
			String name = "Calc";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("CalcServer ready and waiting ...");

			// 启动线程服务，等待客户端的调用
			orb.run();
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("CalcServer Exiting ...");
	}
}