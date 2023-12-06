package corbaClient;

import corbaConversion.IConversionRemote;
import corbaConversion.IConversionRemoteHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ConversionClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            // Récupération du service de nommage
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Récupération de la référence de l'objet distant depuis l'annuaire JNDI
            IConversionRemote conversionRemote = IConversionRemoteHelper.narrow(ncRef.resolve_str("ConversionService"));

            // Utilisation de la méthode distante
            double result;
            result = conversionRemote.conversionMontant(10.5f);
            System.out.println("Résultat de la conversion : " + result);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace(System.out);
        }
    }
}

