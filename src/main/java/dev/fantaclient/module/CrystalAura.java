
package dev.fantaclient.module;

public class CrystalAura extends Module {

    private double range = 4.0;
    private double delay = 500.0;
    private boolean showTarget = true;
    private boolean showStatus = true;
    private boolean testMode = true;

    public CrystalAura() {
        super("CrystalAura", ModuleCategory.COMBAT);
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = Math.max(1.0, Math.min(6.0, range));
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = Math.max(50.0, Math.min(2000.0, delay));
    }

    public boolean isShowTarget() {
        return showTarget;
    }

    public void setShowTarget(boolean showTarget) {
        this.showTarget = showTarget;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @Override
    public void onEnable() {
        System.out.println("[FantaClient] CrystalAura enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[FantaClient] CrystalAura disabled");
    }
}
