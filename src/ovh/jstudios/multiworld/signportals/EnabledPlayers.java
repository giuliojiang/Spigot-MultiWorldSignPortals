package ovh.jstudios.multiworld.signportals;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// This class contains runtime players that have a "charge" to enable a sign as a
// MultiWorld portal
public class EnabledPlayers {

    // Contains player UUID strings
    private final Set<UUID> chargedPlayers = new HashSet<>();

    public void chargePlayer(UUID uuid) {

        chargedPlayers.add(uuid);

    }

    // Returns true if a charge was found and consumed
    // Returns false if the player had no charge
    public boolean consumeCharge(UUID uuid) {

        if (chargedPlayers.contains(uuid)) {
            chargedPlayers.remove(uuid);
            return true;
        } else {
            return false;
        }

    }

}
