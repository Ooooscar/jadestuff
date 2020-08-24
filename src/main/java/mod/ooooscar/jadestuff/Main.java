package mod.ooooscar.jadestuff;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "jadestuff";
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        LOGGER.debug("Hello from JadeStuff!");
    }
}
