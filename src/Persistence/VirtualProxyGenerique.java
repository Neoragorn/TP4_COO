/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

/**
 *
 * @author sofian
 */
import domaine.Personne;
import java.lang.reflect.*;
import java.util.*;

/*
 * Dans cet exemple, on veut montrer comment creer un virtual proxy generique, qui va pouvoir servir 
 * chaque fois qu'on aura besoin de virtual proxy pour une classe donnée. On va utiliser la reflexion
 * pour pouvoir faire cela sans devoir refaire un VirtualProxy specifique pour chaque classe.
 *
 * Ainsi, pour n'importe quel type (classe) T, chaque fois qu'un objet instance de T sera attendu
 * (par exemple pour un attribut d'objet du domaine), on pourra à la place renvoyer un virtual proxy,
 * qui va initialiser/remonter/construire l'objet réel la premiere fois qu'une méthode sera appelée.
 *
 * Pour ce faire, on va creer deux choses, un builder de virtual proxy VirtualProxyBuilder<T> qui va 
 * pouvoir construire un virtual proxy pour le type T (T étant le type de l'objet "réel" qui sera derrière
 * le virtual proxy).
 *
 * On va aussi créer une interface Factory<T> qui va permettre de représenter des fabriques qui vont gérer
 * l'initialisation de l'objet T.
 *
 *
 */
 


class VirtualProxyGenerique {

    /*
     * Les classes qui implémenteront Factory<T> représenteront une fabrique de T. Une fabrique de T
     * va etre utilisée par notre virtual proxy générique pour créer un objet de type T.
     *
     * Le code qui va se charger de créer l'objet de type T sera donc dans la factory, pour eviter
     * de devoir mettre du code spécifique dans le VirtualProxyBuilder, qui doit rester générique.
     */
     
    static interface Factory<T> {
        T create();
    }
    
    /*
     * Le VirtualProxyBuilder<T> est une classe qui va construire un virtual proxy pour un objet de type T.
     * Attention a ne pas confondre le VirtualProxyBuilder<T>, et le virtual proxy (qui est l'objet renvoye
     * par la methode getProxy())
     */
    static class VirtualProxyBuilder<T> implements InvocationHandler {
        /* C'est l'objet "reel", celui pour lequel on fait le proxy. Il est null au debut, il sera cree lors du premier appel de methode. */
        T realObject = null; 
        
        /* C'est la fabrique qui nous permettra de creer l'objet realObject, au moment opportun. */
        Factory<T> factory; 
        
        /*
         * iface c'est l'objet qui va representer le type T.
         * Ca sera egal a T.class, sauf qu'on ne peut pas ecrire ca en Java a cause du type erasure.
         */
        Class<?> iface;
        /*
         * On passe iface (qui doit représenter le type T), par exemple si on cree un VirtualProxyBuilder<Toto>
         * alors iface doit etre Toto.class. Si on ne respecte pas ceci, on aura une exception lors de l'appel
         * de getProxy()
         */
        public VirtualProxyBuilder(Class<?> iface, Factory<T> factory) {
            this.iface = iface;
            this.factory = factory;
        }
        
        /*
         * getProxy() va creer un virtual proxy de T, et le retourner.
         * On appelle Proxy.newProxyInstance(), qui fait partie de l'API reflexion Java.
         * Le @SuppressWarnings() est la pour éviter le warning à propos du cast (T),
         * en effet rien ne permet au compilateur Java d'être sur que au moment de l'execution
         * le retour de Proxy.newProxyInstance() sera bien de type T.
         *
         * Par contre nous on peut en etre sur a partir du moment où iface represente bien le type T.
         */
        public T getProxy() {
            @SuppressWarnings("unchecked")
            T r  = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface }, this);
            System.out.println("PROXY: On a cree le virtual proxy!");
            return r;
        }

        /*
         * Cette methode sera appellee automatiquement (via la reflexion Java) chaque fois qu'une methode
         * va etre appelée sur le virtual proxy (c-a-d sur l'objet retourné par getProxy())
         *
         * On va simplement relayer l'appel (a l'aide de la reflexion) sur l'objet réel, realObject.
         *
         * Si realObject est null, alors on le créé à l'aide de la factory.
         */
 	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
 	        System.out.println("PROXY: On a appelle la methode " + method.getName() + " sur le virtual proxy!");
 	        if (realObject == null) {
 	            System.out.println("PROXY: On initialise l'objet proxyfié maintenant");
 	            realObject = factory.create();
 	        }
 	        System.out.println("PROXY: On appelle la methode sur l'objet reel.");
 	        return method.invoke(realObject, args);
  	}
    }
       
    static class PersonneFactory implements Factory<List<Personne> > {
        public List<Personne> create() {
            ArrayList<Personne> al = new ArrayList<Personne>();
            
            return al;
        }
    }
}
