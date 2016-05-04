package com.company.Data;

/**
 * Created by Nicu Stejar on 23-Feb-16.
 */
public class EngineData {
    private String speedType;
    private float speed;
    private int throttleMin, throttleMax, throttle;
    private int trainBrakeControl, trainBrakeControlMin, trainBrakeControlMax;
    private int engineBreakControl, engineBreakControlMin, engineBreakControlMax;
    private int dynamicBreak, dynamicBreakMin, dynamicBreakMax;
    private int emergencyBreak;
    private int reverser, reverserMin, reverserMax;
    private int ammeter;
    private int RPM;
    private int AWS, AWSWarnCount;
    private float trainBrakeCylinderPressureBAR;
    private float brakePipePressureBAR;
    private float mainReservoirPressureBAR;
    private int currentSpeedLimit;
    private int nextSpeedLimit;
    private float nextSpeedLimitDistance;
    private int signalType, signalState, signalAspect;
    private float signalDistance;

    /**
     * Creates an empty object with fields for parameters of the train
     */
    public EngineData() {
        ;
    }

    public String getSpeedType() {
        return speedType;
    }

    public void setSpeedType(String speedType) {
        this.speedType = speedType;
    }

    public int getSpeed() {
        return Math.round(speed);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getThrottleMin() {
        return throttleMin;
    }

    public void setThrottleMin(int throttleMin) {
        this.throttleMin = throttleMin;
    }

    public int getThrottleMax() {
        return throttleMax;
    }

    public void setThrottleMax(int throttleMax) {
        this.throttleMax = throttleMax;
    }

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public int getTrainBrakeControl() {
        return trainBrakeControl;
    }

    public void setTrainBrakeControl(int trainBrakeControl) {
        this.trainBrakeControl = trainBrakeControl;
    }

    public int getTrainBrakeControlMin() {
        return trainBrakeControlMin;
    }

    public void setTrainBrakeControlMin(int trainBrakeControlMin) {
        this.trainBrakeControlMin = trainBrakeControlMin;
    }

    public int getTrainBrakeControlMax() {
        return trainBrakeControlMax;
    }

    public void setTrainBrakeControlMax(int trainBrakeControlMax) {
        this.trainBrakeControlMax = trainBrakeControlMax;
    }

    public int getEngineBreakControl() {
        return engineBreakControl;
    }

    public void setEngineBreakControl(int engineBreakControl) {
        this.engineBreakControl = engineBreakControl;
    }

    public int getEngineBreakControlMin() {
        return engineBreakControlMin;
    }

    public void setEngineBreakControlMin(int engineBreakControlMin) {
        this.engineBreakControlMin = engineBreakControlMin;
    }

    public int getEngineBreakControlMax() {
        return engineBreakControlMax;
    }

    public void setEngineBreakControlMax(int engineBreakControlMax) {
        this.engineBreakControlMax = engineBreakControlMax;
    }

    public int getDynamicBreak() {
        return dynamicBreak;
    }

    public void setDynamicBreak(int dynamicBreak) {
        this.dynamicBreak = dynamicBreak;
    }

    public int getDynamicBreakMin() {
        return dynamicBreakMin;
    }

    public void setDynamicBreakMin(int dynamicBreakMin) {
        this.dynamicBreakMin = dynamicBreakMin;
    }

    public int getDynamicBreakMax() {
        return dynamicBreakMax;
    }

    public void setDynamicBreakMax(int dynamicBreakMax) {
        this.dynamicBreakMax = dynamicBreakMax;
    }

    public int getEmergencyBreak() {
        return emergencyBreak;
    }

    public void setEmergencyBreak(int emergencyBreak) {
        this.emergencyBreak = emergencyBreak;
    }

    public int getReverser() {
        return reverser;
    }

    public void setReverser(int reverser) {
        this.reverser = reverser;
    }

    public int getReverserMin() {
        return reverserMin;
    }

    public void setReverserMin(int reverserMin) {
        this.reverserMin = reverserMin;
    }

    public int getReverserMax() {
        return reverserMax;
    }

    public void setReverserMax(int reverserMax) {
        this.reverserMax = reverserMax;
    }

    public int getAmmeter() {
        return ammeter;
    }

    public void setAmmeter(int ammeter) {
        this.ammeter = ammeter;
    }

    public int getRPM() {
        return RPM;
    }

    public void setRPM(int RPM) {
        this.RPM = RPM;
    }

    public int getAWS() {
        return AWS;
    }

    public void setAWS(int AWS) {
        this.AWS = AWS;
    }

    public int getAWSWarnCount() {
        return AWSWarnCount;
    }

    public void setAWSWarnCount(int AWSWarnCount) {
        this.AWSWarnCount = AWSWarnCount;
    }

    public float getTrainBrakeCylinderPressureBAR() {
        return trainBrakeCylinderPressureBAR;
    }

    public void setTrainBrakeCylinderPressureBAR(float trainBrakeCylinderPressureBAR) {
        this.trainBrakeCylinderPressureBAR = trainBrakeCylinderPressureBAR;
    }

    public float getBrakePipePressureBAR() {
        return brakePipePressureBAR;
    }

    public void setBrakePipePressureBAR(float brakePipePressureBAR) {
        this.brakePipePressureBAR = brakePipePressureBAR;
    }

    public float getMainReservoirPressureBAR() {
        return mainReservoirPressureBAR;
    }

    public void setMainReservoirPressureBAR(float mainReservoirPressureBAR) {
        this.mainReservoirPressureBAR = mainReservoirPressureBAR;
    }

    public int getCurrentSpeedLimit() {
        return currentSpeedLimit;
    }

    public void setCurrentSpeedLimit(int currentSpeedLimit) {
        this.currentSpeedLimit = currentSpeedLimit;
    }

    public int getNextSpeedLimit() {
        return nextSpeedLimit;
    }

    public void setNextSpeedLimit(int nextSpeedLimit) {
        this.nextSpeedLimit = nextSpeedLimit;
    }

    public float getNextSpeedLimitDistance() {
        return nextSpeedLimitDistance;
    }

    public void setNextSpeedLimitDistance(float nextSpeedLimitDistance) {
        this.nextSpeedLimitDistance = nextSpeedLimitDistance;
    }

    public int getSignalType() {
        return signalType;
    }

    public void setSignalType(int signalType) {
        this.signalType = signalType;
    }

    public int getSignalState() {
        return signalState;
    }

    public void setSignalState(int signalState) {
        this.signalState = signalState;
    }

    public int getSignalAspect() {
        return signalAspect;
    }

    public void setSignalAspect(int signalAspect) {
        this.signalAspect = signalAspect;
    }

    public float getSignalDistance() {
        return signalDistance;
    }

    public void setSignalDistance(float signalDistance) {
        this.signalDistance = signalDistance;
    }

}
