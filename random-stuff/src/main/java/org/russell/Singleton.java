package org.russell;

public class Singleton {

    public static final Singleton singleton = new Singleton(); 
    
    private Singleton() {
        if (singleton != null) {
            throw new RuntimeException("Nope!");
        }
    }
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        System.out.println(singleton);
        
        Class<Singleton> singletonClass = (Class<Singleton>) Class.forName("org.russell.Singleton");
        Singleton singletonReflection = singletonClass.newInstance();
        
        System.out.println(singleton == singletonReflection);
    }
}
