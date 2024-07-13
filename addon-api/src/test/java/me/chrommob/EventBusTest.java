package me.chrommob;

import me.chrommob.minestore.addons.events.*;
import me.chrommob.minestore.addons.events.types.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventBusTest {
    private boolean disabled = false;
    private boolean enabled = false;
    private boolean loaded = false;
    private boolean purchase = false;
    private boolean reload = false;
    private boolean customEvent = false;

    @Test
    public void testDisable() {
        MineStoreEventBus.registerListener(MineStoreDisableEvent.class, event -> {
            disabled = true;
        });
        new MineStoreDisableEvent().call();
        Assertions.assertTrue(disabled);
    }

    @Test
    public void testEnable() {
        MineStoreEventBus.registerListener(MineStoreEnableEvent.class, event -> {
            enabled = true;
        });
        new MineStoreEnableEvent().call();
        Assertions.assertTrue(enabled);
    }

    @Test
    public void testLoad() {
        MineStoreEventBus.registerListener(MineStoreLoadEvent.class, event -> {
            loaded = true;
        });
        new MineStoreLoadEvent().call();
        Assertions.assertTrue(loaded);
    }

    @Test
    public void testPurchase() {
        MineStoreEventBus.registerListener(MineStorePurchaseEvent.class, event -> {
            Assertions.assertEquals("test", event.username());
            Assertions.assertEquals("test", event.command());
            Assertions.assertEquals(0, event.id());
            Assertions.assertEquals(MineStorePurchaseEvent.COMMAND_TYPE.ONLINE, event.commandType());
            purchase = true;
        });
        new MineStorePurchaseEvent("test", "test", 0, MineStorePurchaseEvent.COMMAND_TYPE.ONLINE).call();
        Assertions.assertTrue(purchase);
    }

    @Test
    public void testReload() {
        MineStoreEventBus.registerListener(MineStoreReloadEvent.class, event -> {
            reload = true;
        });
        new MineStoreReloadEvent().call();
        Assertions.assertTrue(reload);
    }

    @Test
    public void testCustomEvent() {
        MineStoreEventBus.registerListener(CustomEventExample.class, event -> {
            Assertions.assertEquals("test", event.getMessage());
            customEvent = true;
        });
        new CustomEventExample("test").call();
        Assertions.assertTrue(customEvent);
    }
}
