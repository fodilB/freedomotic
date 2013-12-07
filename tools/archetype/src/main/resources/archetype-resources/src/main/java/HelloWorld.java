 #set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package $

{package};

import ${groupId}.api.EventTemplate;
import ${groupId}.api.Protocol;
import ${groupId}.app.Freedomotic;
import ${groupId}.exceptions.UnableToExecuteException;
import ${groupId}.reactions.Command;
import java.io.IOException;

public class HelloWorld extends Protocol {

    final int POLLING_WAIT;

    public HelloWorld() {
        //every plugin needs a name and a manifest XML file
        super("HelloWorld", "/com.freedomotic.hello/${project.artifactId}-manifest.xml");
        //read a property from the manifest file below which is in
        //FREEDOMOTIC_FOLDER/plugins/devices/${groupId}.hello/${project.artifactId}.xml
        POLLING_WAIT = configuration.getIntProperty("time-between-reads", 2000);
        //POLLING_WAIT is the value of the property "time-between-reads" or 2000 millisecs,
        //default value if the property does not exist in the manifest
        setPollingWait(POLLING_WAIT); //millisecs interval between hardware device status reads
    }

    @Override
    protected void onShowGui() {
        /**
         * uncomment the line below to add a GUI to this plugin the GUI can be
         * started with a right-click on plugin list on the desktop frontend
         * (${groupId}.jfrontend plugin)
         */
        //bindGuiToPlugin(new HelloWorldGui(this));
    }

    @Override
    protected void onHideGui() {
        //implement here what to do when the this plugin GUI is closed
        //for example you can change the plugin description
        setDescription("My GUI is now hidden");
    }

    @Override
    protected void onRun() {
        Freedomotic.logger.info("HelloWorld onRun() logs this message every "
                + "POLLINGWAIT=" + POLLING_WAIT + "milliseconds");
        //at the end of this method the system waits POLLINGTIME 
        //before calling it again. The result is this log message is printed
        //every 2 seconds (2000 millisecs)
    }

    @Override
    protected void onStart() {
        Freedomotic.logger.info("HelloWorld plugin is started");
    }

    @Override
    protected void onStop() {
        Freedomotic.logger.info("HelloWorld plugin is stopped ");
    }

    @Override
    protected void onCommand(Command c) throws IOException, UnableToExecuteException {
        Freedomotic.logger.info("HelloWorld plugin receives a command called " + c.getName()
                + " with parameters " + c.getProperties().toString());
    }

    @Override
    protected boolean canExecute(Command c) {
        //don't mind this method for now
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onEvent(EventTemplate event) {
        //don't mind this method for now
        throw new UnsupportedOperationException("Not supported yet.");
    }
}