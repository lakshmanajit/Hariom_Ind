package com.traffsys.DTO;

public class DashboardDto {

    private Long totalProducts;
    private Long totalStages;
    private Long totalDefects;
    private Long totalInspections;
    private Long totalLotBatches;
    private Long totalQcResults;
    private Long passCount;
    private Long failCount;

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Long getTotalStages() {
        return totalStages;
    }

    public void setTotalStages(Long totalStages) {
        this.totalStages = totalStages;
    }

    public Long getTotalDefects() {
        return totalDefects;
    }

    public void setTotalDefects(Long totalDefects) {
        this.totalDefects = totalDefects;
    }

    public Long getTotalInspections() {
        return totalInspections;
    }

    public void setTotalInspections(Long totalInspections) {
        this.totalInspections = totalInspections;
    }

    public Long getTotalLotBatches() {
        return totalLotBatches;
    }

    public void setTotalLotBatches(Long totalLotBatches) {
        this.totalLotBatches = totalLotBatches;
    }

    public Long getTotalQcResults() {
        return totalQcResults;
    }

    public void setTotalQcResults(Long totalQcResults) {
        this.totalQcResults = totalQcResults;
    }

    public Long getPassCount() {
        return passCount;
    }

    public void setPassCount(Long passCount) {
        this.passCount = passCount;
    }

    public Long getFailCount() {
        return failCount;
    }

    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }
}
