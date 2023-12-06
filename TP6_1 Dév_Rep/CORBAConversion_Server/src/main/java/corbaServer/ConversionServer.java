package corbaServer;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import service.ConversionImpl;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            // Création et activation de l'objet distant
            ConversionImpl conversionImpl = new ConversionImpl();
            orb.connect(conversionImpl);

            // Récupération du service de nommage
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Liaison de l'objet distant avec un nom dans l'annuaire JNDI
            NameComponent path[] = ncRef.to_name("ConversionService");
            ncRef.rebind(path, conversionImpl);

            System.out.println("Serveur prêt...");
            orb.run();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace(System.out);
        }
    }
}


