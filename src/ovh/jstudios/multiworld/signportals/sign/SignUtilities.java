package ovh.jstudios.multiworld.signportals.sign;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import ovh.jstudios.multiworld.signportals.StringConstants;

public final class SignUtilities {

    // Public methods =========================================================

    public static Sign getSignState(Block block) {

        BlockState state = block.getState();
        if (state instanceof Sign) {
            return (Sign) state;
        } else {
            return null;
        }

    }

    // Returns true if successful
    // False if failed
    public static boolean enableSignAsPortal(Sign sign) {

        if (isPartialPortalSign(sign)) {
            sign.setLine(3, StringConstants.METADATA_SIGN_PORTAL_MAGIC);
            sign.update();
            return true;
        } else {
            return false;
        }

    }

    public static String getPortalDestination(Sign sign) {

        if (!isPortalSign(sign)) {
            return null;
        }

        return sign.getLine(1);

    }

    // A full portal sign has the [mw] header, and
    // the magic footer
    public static boolean isPortalSign(Sign sign) {

        // Check the magic line
        String magicLine = sign.getLine(3);
        if (!StringConstants.METADATA_SIGN_PORTAL_MAGIC.equals(magicLine)) {
            return false;
        }

        return isPartialPortalSign(sign);

    }

    // A partial portal sign has the [mw] header,
    // but not necessarily the magic footer
    public static boolean isPartialPortalSign(Sign sign) {

        // Check the first line
        String firstLine = sign.getLine(0);
        return "[mw]".equals(firstLine);

    }


}
