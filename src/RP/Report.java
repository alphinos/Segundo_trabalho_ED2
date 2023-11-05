package RP;

import java.lang.management.ManagementFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Report {

    public static void writeReport( String path, String[] content ){
        File file = new File( path );

        if ( file.exists() ){
            try {
                if ( file.canWrite() ){
                    PrintWriter output = new PrintWriter( file );
                    output.println( soft_hard_content() + "\n" );

                    for ( String page : content )
                        output.println( page );

                    output.close();
                }
            } catch (FileNotFoundException ex ) {
                System.out.println(ex.toString());
            }
        } else {
            try {
                if ( file.createNewFile() && file.canWrite() ){
                    PrintWriter output = new PrintWriter( file );
                    output.println( soft_hard_content() + "\n" );
                    for ( String page : content )
                        output.println( page );
                    output.close();
                }
            }  catch ( IOException ex ) {
                System.out.println(ex.toString());
            }
        }
    }

    public static String soft_hard_content() {
        String userOS = "user.name";
        String nameOS = "os.name";
        String versionOS = "os.version";
        String architectureOS = "os.arch";

        String informationType = "The information about OS";

        String currentUsername = "Current username: " + System.getProperty(userOS);

        String name_OS = "Name of the OS: " + System.getProperty(nameOS);

        String version_OS = "Version of the OS: " + System.getProperty(versionOS);

        String architecture_OS = "Architecture of THe OS: " + System.getProperty(architectureOS);

        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        /* Total number of processors or cores available to the JVM */
        String available_cores = "Available processors (cores): " + Runtime.getRuntime().availableProcessors();

        /* Total amount of free memory available to the JVM */
        String free_available_memory = "Free memory (bytes): " + (Runtime.getRuntime().freeMemory() / (1024 * 1024));

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        /* Maximum amount of memory the JVM will attempt to use */
        String maximum_memory = "Maximum memory (bytes): " + maxMemory;

        /* Total memory currently available to the JVM */
        String total_memory_available = "Total memory available to JVM (bytes): " + (Runtime.getRuntime().totalMemory() / (1024 * 1024));

        long physicalMemorySize = os.getTotalMemorySize() / (1024 * 1024 * 1024);
        long freePhysicalMemory = os.getFreeMemorySize() / (1024 * 1024);

        String total_RAM = "physicalMemorySize: " + physicalMemorySize;
        String free_RAM = "freePhysicalMemory: " + freePhysicalMemory;

        String pc_owner = switch (currentUsername) {
            case "Current username: alphinos" -> "Carlos Veras";
            case "Current username: Andrei" -> "Andrei Ramos";
            default -> "Desconhecido";
        };

        String informacoes_PC = "" + informationType + "\n" +
        currentUsername + "\n" +
        name_OS + "\n" +
        version_OS + "\n" +
        architecture_OS + "\n" +
        available_cores + "\n" +
        free_available_memory + "\n" +
        maximum_memory + "\n" +
        total_memory_available + "\n" +
        total_RAM + "\n" +
        free_RAM + "\n" + "\n" +
        "Este relatório foi gerado pelo computador de " + pc_owner;

        return informacoes_PC;
    }
}
