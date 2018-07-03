package com.vcredit.persistence;

/**
 * 发票记录表
 **/
public class Invoice {
	private static final long serialVersionUID = -8819586243811218246L;
	private Long id;
    /** 订单ID **/
    private Long orderId;
    /** 发票唯一请求id **/
    private String invoiceApplyNo;
    /** 发票保存ftp路径 **/
    private String savePath;
    /** 状态,参考InvoiceProcessStatusEnum **/
    private Integer status;
    /** 用户ID **/
    private Long custId;
    /** 邮箱,发票生成后会发送邮件给客户 **/
    private String email;
    /** 发票类型,1电子发票 **/
    private Integer invoiceType;
    /** 抬头类型,1个人,2单位 **/
    private Integer titleType;
    /** 抬头 **/
    private String title;
    /** 内容类型,1明细 **/
    private Integer contentType;
    /** 单位类型，需要识别号 */
    private String Ghfnsrsbh;
    
    private String fpDm;//发票代码
	private String fpHm;//发票号码

	private String redInvoiceApplyNo;
	private String redSavePath;
	
	private Integer redStatus;//冲红状态，0代表未冲红，1代表冲红成功，2代表需要冲红

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
    public String getInvoiceApplyNo(){
        return invoiceApplyNo;
    }
    public void setInvoiceApplyNo(String invoiceApplyNo){
        this.invoiceApplyNo = invoiceApplyNo;
    }
    public String getSavePath(){
        return savePath;
    }
    public void setSavePath(String savePath){
        this.savePath = savePath;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Long getCustId(){
        return custId;
    }
    public void setCustId(Long custId){
        this.custId = custId;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public Integer getInvoiceType(){
        return invoiceType;
    }
    public void setInvoiceType(Integer invoiceType){
        this.invoiceType = invoiceType;
    }
    public Integer getTitleType(){
        return titleType;
    }
    public void setTitleType(Integer titleType){
        this.titleType = titleType;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public Integer getContentType(){
        return contentType;
    }
    public void setContentType(Integer contentType){
        this.contentType = contentType;
    }
	public String getGhfnsrsbh() {
		return Ghfnsrsbh;
	}
	public void setGhfnsrsbh(String ghfnsrsbh) {
		Ghfnsrsbh = ghfnsrsbh;
	}
	public String getFpDm() {
		return fpDm;
	}
	public void setFpDm(String fpDm) {
		this.fpDm = fpDm;
	}
	public String getFpHm() {
		return fpHm;
	}
	public void setFpHm(String fpHm) {
		this.fpHm = fpHm;
	}
	public Integer getRedStatus() {
		return redStatus;
	}
	public void setRedStatus(Integer redStatus) {
		this.redStatus = redStatus;
	}
	public String getRedInvoiceApplyNo() {
		return redInvoiceApplyNo;
	}
	public void setRedInvoiceApplyNo(String redInvoiceApplyNo) {
		this.redInvoiceApplyNo = redInvoiceApplyNo;
	}
	public String getRedSavePath() {
		return redSavePath;
	}
	public void setRedSavePath(String redSavePath) {
		this.redSavePath = redSavePath;
	}
	
	
}