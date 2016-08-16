package com.delmar.cargo.model;

import java.sql.Timestamp;
import java.util.List;

public class CustomHead {
	private String ie_flag; 
	private String pre_entry_id; 
	private String customs_id;
	private String manual_no; 
	private String contr_no; 
	private String i_e_date; 
	private String d_date; 
	private String trade_co; 
	private String trade_name; 
	private String owner_code; 
	private String owner_name; 
	private String agent_code;
	private String agent_name; 
	private String traf_mode;
	private String traf_mode_jie; 
	private String traf_name; 
	private String voyage_no; 
	private String bill_no; 
	private String trade_mode; 
	private String trade_mode_jie; 
	private String cut_mode; 
	private String cut_mode_jie; 
	private String in_ratio; 
	private String pay_way; 
	private String pay_way_jie; 
	private String lisence_no; 
	private String trade_country; 
	private String trade_country_jie; 
	private String distinate_port; 
	private String distinate_port_jie; 
	private String district_code; 
	private String district_code_jie; 
	private String appr_no; 
	private String trans_mode; 
	private String trans_mode_jie; 
	private String fee_mark;
	private String fee_rate; 
	private String fee_curr; 
	private String insur_mark;
	private String insur_rate; 
	private String insur_curr; 
	private String other_mark;
	private String other_rate; 
	private String other_curr; 
	private String pack_no; 
	private String wrap_type; 
	private String wrap_type_jie; 
	private String gross_wt; 
	private String net_wt; 
	private String ex_source; 
	private String type_er; 
	private String entry_group; 
	private String is_status; 
	private String username; 
	private Timestamp create_date; 
	private String del_flag; 
	private String RaDeclNo; 
	private String RaManualNo; 
	private String StoreNo; 
	private String PrdtID; 
	private String i_e_port; 
	private String note_s; 
	private String print_date; 
	private String edi_id;
	private Integer flag; 
	private String archive_no; 
	private Timestamp archive_date; 
	private String container_no; 
	private String hbl_no; 
	private String i_e_port_jie;
	private List<CustomList> customList;
	
	
	public String getIe_flag() {
		return ie_flag;
	}
	public void setIe_flag(String ie_flag) {
		this.ie_flag = ie_flag;
	}
	public String getPre_entry_id() {
		return pre_entry_id;
	}
	public void setPre_entry_id(String pre_entry_id) {
		this.pre_entry_id = pre_entry_id;
	}
	public String getCustoms_id() {
		return customs_id;
	}
	public void setCustoms_id(String customs_id) {
		this.customs_id = customs_id;
	}
	public String getManual_no() {
		return manual_no;
	}
	public void setManual_no(String manual_no) {
		this.manual_no = manual_no;
	}
	public String getContr_no() {
		return contr_no;
	}
	public void setContr_no(String contr_no) {
		this.contr_no = contr_no;
	}
	public String getI_e_date() {
		return i_e_date;
	}
	public void setI_e_date(String i_e_date) {
		this.i_e_date = i_e_date;
	}
	public String getD_date() {
		return d_date;
	}
	public void setD_date(String d_date) {
		this.d_date = d_date;
	}
	public String getTrade_co() {
		return trade_co;
	}
	public void setTrade_co(String trade_co) {
		this.trade_co = trade_co;
	}
	public String getTrade_name() {
		return trade_name;
	}
	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}
	public String getOwner_code() {
		return owner_code;
	}
	public void setOwner_code(String owner_code) {
		this.owner_code = owner_code;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getAgent_code() {
		return agent_code;
	}
	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getTraf_mode() {
		return traf_mode;
	}
	public void setTraf_mode(String traf_mode) {
		this.traf_mode = traf_mode;
	}
	public String getTraf_mode_jie() {
		return traf_mode_jie;
	}
	public void setTraf_mode_jie(String traf_mode_jie) {
		this.traf_mode_jie = traf_mode_jie;
	}
	public String getTraf_name() {
		return traf_name;
	}
	public void setTraf_name(String traf_name) {
		this.traf_name = traf_name;
	}
	public String getVoyage_no() {
		return voyage_no;
	}
	public void setVoyage_no(String voyage_no) {
		this.voyage_no = voyage_no;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public String getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(String trade_mode) {
		this.trade_mode = trade_mode;
	}
	public String getTrade_mode_jie() {
		return trade_mode_jie;
	}
	public void setTrade_mode_jie(String trade_mode_jie) {
		this.trade_mode_jie = trade_mode_jie;
	}
	public String getCut_mode() {
		return cut_mode;
	}
	public void setCut_mode(String cut_mode) {
		this.cut_mode = cut_mode;
	}
	public String getCut_mode_jie() {
		return cut_mode_jie;
	}
	public void setCut_mode_jie(String cut_mode_jie) {
		this.cut_mode_jie = cut_mode_jie;
	}
	public String getIn_ratio() {
		return in_ratio;
	}
	public void setIn_ratio(String in_ratio) {
		this.in_ratio = in_ratio;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	public String getPay_way_jie() {
		return pay_way_jie;
	}
	public void setPay_way_jie(String pay_way_jie) {
		this.pay_way_jie = pay_way_jie;
	}
	public String getLisence_no() {
		return lisence_no;
	}
	public void setLisence_no(String lisence_no) {
		this.lisence_no = lisence_no;
	}
	public String getTrade_country() {
		return trade_country;
	}
	public void setTrade_country(String trade_country) {
		this.trade_country = trade_country;
	}
	public String getTrade_country_jie() {
		return trade_country_jie;
	}
	public void setTrade_country_jie(String trade_country_jie) {
		this.trade_country_jie = trade_country_jie;
	}
	public String getDistinate_port() {
		return distinate_port;
	}
	public void setDistinate_port(String distinate_port) {
		this.distinate_port = distinate_port;
	}
	public String getDistinate_port_jie() {
		return distinate_port_jie;
	}
	public void setDistinate_port_jie(String distinate_port_jie) {
		this.distinate_port_jie = distinate_port_jie;
	}
	public String getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}
	public String getDistrict_code_jie() {
		return district_code_jie;
	}
	public void setDistrict_code_jie(String district_code_jie) {
		this.district_code_jie = district_code_jie;
	}
	public String getAppr_no() {
		return appr_no;
	}
	public void setAppr_no(String appr_no) {
		this.appr_no = appr_no;
	}
	public String getTrans_mode() {
		return trans_mode;
	}
	public void setTrans_mode(String trans_mode) {
		this.trans_mode = trans_mode;
	}
	public String getTrans_mode_jie() {
		return trans_mode_jie;
	}
	public void setTrans_mode_jie(String trans_mode_jie) {
		this.trans_mode_jie = trans_mode_jie;
	}
	public String getFee_mark() {
		return "000//";
	}
	public void setFee_mark(String fee_mark) {
		this.fee_mark = fee_mark;
	}
	public String getFee_rate() {
		return fee_rate;
	}
	public void setFee_rate(String fee_rate) {
		this.fee_rate = fee_rate;
	}
	public String getFee_curr() {
		return fee_curr;
	}
	public void setFee_curr(String fee_curr) {
		this.fee_curr = fee_curr;
	}
	public String getInsur_mark() {
		return "000//";
	}
	public void setInsur_mark(String insur_mark) {
		this.insur_mark = insur_mark;
	}
	public String getInsur_rate() {
		return insur_rate;
	}
	public void setInsur_rate(String insur_rate) {
		this.insur_rate = insur_rate;
	}
	public String getInsur_curr() {
		return insur_curr;
	}
	public void setInsur_curr(String insur_curr) {
		this.insur_curr = insur_curr;
	}
	public String getOther_mark() {
		return "000//";
	}
	public void setOther_mark(String other_mark) {
		this.other_mark = other_mark;
	}
	public String getOther_rate() {
		return other_rate;
	}
	public void setOther_rate(String other_rate) {
		this.other_rate = other_rate;
	}
	public String getOther_curr() {
		return other_curr;
	}
	public void setOther_curr(String other_curr) {
		this.other_curr = other_curr;
	}
	public String getPack_no() {
		return pack_no;
	}
	public void setPack_no(String pack_no) {
		this.pack_no = pack_no;
	}
	public String getWrap_type() {
		return wrap_type;
	}
	public void setWrap_type(String wrap_type) {
		this.wrap_type = wrap_type;
	}
	public String getWrap_type_jie() {
		return wrap_type_jie;
	}
	public void setWrap_type_jie(String wrap_type_jie) {
		this.wrap_type_jie = wrap_type_jie;
	}
	public String getGross_wt() {
		return gross_wt;
	}
	public void setGross_wt(String gross_wt) {
		this.gross_wt = gross_wt;
	}
	public String getNet_wt() {
		return net_wt;
	}
	public void setNet_wt(String net_wt) {
		this.net_wt = net_wt;
	}
	public String getEx_source() {
		return ex_source;
	}
	public void setEx_source(String ex_source) {
		this.ex_source = ex_source;
	}
	public String getType_er() {
		return type_er;
	}
	public void setType_er(String type_er) {
		this.type_er = type_er;
	}
	public String getEntry_group() {
		return entry_group;
	}
	public void setEntry_group(String entry_group) {
		this.entry_group = entry_group;
	}
	public String getIs_status() {
		return is_status;
	}
	public void setIs_status(String is_status) {
		this.is_status = is_status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getRaDeclNo() {
		return RaDeclNo;
	}
	public void setRaDeclNo(String raDeclNo) {
		RaDeclNo = raDeclNo;
	}
	public String getRaManualNo() {
		return RaManualNo;
	}
	public void setRaManualNo(String raManualNo) {
		RaManualNo = raManualNo;
	}
	public String getStoreNo() {
		return StoreNo;
	}
	public void setStoreNo(String storeNo) {
		StoreNo = storeNo;
	}
	public String getPrdtID() {
		return PrdtID;
	}
	public void setPrdtID(String prdtID) {
		PrdtID = prdtID;
	}
	public String getI_e_port() {
		return i_e_port;
	}
	public void setI_e_port(String i_e_port) {
		this.i_e_port = i_e_port;
	}
	public String getNote_s() {
		return note_s;
	}
	public void setNote_s(String note_s) {
		this.note_s = note_s;
	}
	public String getPrint_date() {
		return print_date;
	}
	public void setPrint_date(String print_date) {
		this.print_date = print_date;
	}
	public String getEdi_id() {
		return edi_id;
	}
	public void setEdi_id(String edi_id) {
		this.edi_id = edi_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getArchive_no() {
		return archive_no;
	}
	public void setArchive_no(String archive_no) {
		this.archive_no = archive_no;
	}
	public Timestamp getArchive_date() {
		return archive_date;
	}
	public void setArchive_date(Timestamp archive_date) {
		this.archive_date = archive_date;
	}
	public String getContainer_no() {
		return container_no;
	}
	public void setContainer_no(String container_no) {
		this.container_no = container_no;
	}
	public String getHbl_no() {
		return hbl_no;
	}
	public void setHbl_no(String hbl_no) {
		this.hbl_no = hbl_no;
	}
	public String getI_e_port_jie() {
		return i_e_port_jie;
	}
	public void setI_e_port_jie(String i_e_port_jie) {
		this.i_e_port_jie = i_e_port_jie;
	}
	public List<CustomList> getCustomList() {
		return customList;
	}
	public void setCustomList(List<CustomList> customList) {
		this.customList = customList;
	} 
	
	
	
}