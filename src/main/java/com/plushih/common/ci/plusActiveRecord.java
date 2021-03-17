package com.plushih.common.ci;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.FieldInfoEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 코드이그나이터처럼 사용하기위함
 * plusActiveRecord  like CodeIgniter
 */
public class plusActiveRecord extends plusQueryBuilder {

	public plusActiveRecord(){

	};
	public plusActiveRecord(HttpServletRequest request){
        this.request = request;
        this.input = new Input(request, RequestMethod.POST);
        if(!StringUtils.isEmpty(input.post("draw"))){
			this.draw = Integer.parseInt(input.post("draw"));
		}
        if(!StringUtils.isEmpty(input.post("start")) && !StringUtils.isEmpty(input.post("length"))){
        	this.limit(Integer.parseInt(input.post("length")),Integer.parseInt(input.post("start")));
		}
    }
    public plusActiveRecord(String id, HttpServletRequest request){
		this._id = id;
        this.request = request;
        this.input = new Input(request, RequestMethod.POST);
        if(!StringUtils.isEmpty(input.post("start")) && !StringUtils.isEmpty(input.post("length"))){
        	this.limit(Integer.parseInt(input.post("length")),Integer.parseInt(input.post("start")));
		}
        if(!StringUtils.isEmpty(input.post("draw"))){
			this.draw = Integer.parseInt(input.post("draw"));
		}
        //this.loginSession = new LoginSession(request.getSession());

    }

    public String getYmdHis(){
		java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return  (formatter.format(new java.util.Date()));
	}


	public String getReferer(){
		String ref="";
		if(this.request!=null){
			//ref = this.request.getHeader("referer");
            if(StringUtils.isEmpty(ref)){
                ref = this.request.getRequestURI();
            }
			this._LOCATION = ref;
		}
		return ref;
	}
	public String getUmId(){
		String id="NONE";
		if(this.request!=null){
			try{
				if(this.request.getSession()!=null){
					id = this.request.getSession().getAttribute(LoginSession.ID).toString();
					String seq= this.request.getSession().getAttribute(LoginSession.SEQ).toString();

					this._USERSEQ = seq;
				}
			} catch(Exception ex){

			}

			this._USERID = id;
		}
		return this._USERID;
	}
	public String getUmSeq(){
		String seq="";
		if(this.request!=null){
			try{
				if(this.request.getSession()!=null){
					seq= this.request.getSession().getAttribute(LoginSession.SEQ).toString();

				}
			} catch(Exception ex){

			}

		}
		return seq;
	}

	// ------------------- Active Record Query -------------------
	// - SELECT
	public plusActiveRecord select(String str) {
		this._select = str;
		return this;
	}
	public plusActiveRecord setid(String str) {
		this._id = str;
		return this;
	}

	public plusActiveRecord from(String table) {
		this._table = table;
		return this;
	}

	// - Join
	public plusActiveRecord join(String table, String clausure, String joinType) {
		this._join.put(joinType.toUpperCase() +" JOIN " +table , clausure);
		return this;
	}
	public plusActiveRecord requre(){
		requireState = true;
		return this;
	}
	public plusActiveRecord requre(int min,int max){
		requireState = true;

		return this;
	}
	public plusActiveRecord minChar(int min){
		minChar=min;
		return this;
	}
	public plusActiveRecord maxChar(int max){
		maxChar=max;
		return this;
	}
	public plusActiveRecord requreMax(){
		requireState = true;
		return this;
	}
	public plusActiveRecord resetVali(){
		requireState = true;
		minChar=0;
		maxChar=0;
		return this;
	}
	public plusActiveRecord join(String table, String clausure) {
		return this.join(table, clausure, "inner");
	}

	// - WHERE
	public plusActiveRecord where(String column, String value) throws Exception {
		if(requireState==true){
			if(StringUtils.isEmpty(value)){
				resetVali();
				throw new Exception("해당컬럼 ["+column+"]의 값은 필수값입니다.");
			}
		}

//		this._where.put(column, (scape ? "'" + value + "'" : value));
		this._where.put(column, value);
		return this;
	}
	public plusActiveRecord where(String column) throws Exception {
		String aliasName = "";
		String aliasColumn = column;
		Debug.log(column);
		if(column.indexOf(".")>-1){
			String[] words = column.split("\\.");
			Debug.log("words==null===?"+ ( words==null) +"::::words.length"+words.length);
			try{
			aliasName = words[0];
			aliasColumn = words[1];
			} catch( Exception ex){
				ex.printStackTrace();
			}

		}
		return this.where(column, input.post(StringUtils.toCamel(aliasColumn)));
	}
	public plusActiveRecord not(String column, String value) throws Exception {
		this._where_not.put(column,value);
		return this;
	}
	public plusActiveRecord gt(String column, String value) throws Exception {
		this._gt.put(column,value);
		return this;
	}

	public plusActiveRecord gteq(String column, String value) throws Exception {
		this._gteq.put(column,value);
		return this;
	}
	public plusActiveRecord lt(String column, String value) throws Exception {
		this._lt.put(column,value);
		return this;
	}
	public plusActiveRecord lteq(String column, String value) throws Exception {
		this._lteq.put(column,value);
		return this;
	}

//	public plusActiveRecord where(String column, String value) throws Exception {
//		return this.where(column, value);
//	}

//	public plusActiveRecord where(String column, int value) throws Exception {
//		return this.where(column, String.valueOf(value), true);
//	}

//	public plusActiveRecord where(String column, int value, boolean scape) throws Exception {
//		return this.where(column, String.valueOf(value), scape);
//	}
	public plusActiveRecord where(FieldInfoEntity fieldInfoEntity) throws Exception {
		String fieldCamel =StringUtils.toCamel(fieldInfoEntity.getColumnName());
		String val = input.post(fieldCamel);
		if(StringUtils.isEmpty(val)){
			val=fieldInfoEntity.getColumnDefault();
		}
		this.where(fieldCamel,val);
		return this;
	}


	public plusActiveRecord or(String column, String value, boolean scape) {
		this._or.put(column, (scape ? "'" + value + "'" : value));
		return this;
	}



	public plusActiveRecord or(String column, String value) {
		return this.or(column, value, true);
	}

	public plusActiveRecord or(String column, int value) {
		return this.or(column, String.valueOf(value), true);
	}

	public plusActiveRecord or(String column, int value, boolean scape) {
		return this.or(column, String.valueOf(value), scape);
	}

	public plusActiveRecord where_in(String column, String... clause) {
		this._where_in.put(column, clause);
		return this;
	}
	public plusActiveRecord where_not_in(String column, String... clause) {
		this._where_not_in.put(column, clause);
		return this;
	}
	public plusActiveRecord add(FieldInfoEntity fieldInfoEntity) {
		String fieldCamel =StringUtils.toCamel(fieldInfoEntity.getColumnName());
		String val = input.post(fieldCamel);

		if(StringUtils.isEmpty(val)){
			val=fieldInfoEntity.getColumnDefault();
		}

		if(val !=null){
			switch(fieldInfoEntity.getDataType().toUpperCase()){
				default:
					add(fieldInfoEntity.getColumnName(),val);
					break;
				case "DATE":
				case "TIME":
				case "DATETIME":
				case "TIMESTAMP":
					if(val.toUpperCase().trim().equals("NOW()")){
						add(fieldInfoEntity.getColumnName(),val,false);
					} else {
						add(fieldInfoEntity.getColumnName(),val);
					}
					break;
				case "YEAR":
				case "TINYINT":
				case "SMALLINT":
				case "MEDIUMINT":
				case "INT":
				case "BIGINT":
				case "DOUBLE":
				case "FLOAT":
				case "DECIMAL":
					add(fieldInfoEntity.getColumnName(),val,false);
					break;
			}
		}
		return this;
	}
	public plusActiveRecord add(String key,String val,Boolean scope){
		if(val!=null){
			this._values.put(key,scope?StringUtils.addSlashes(val):StringUtils.addSlashes(val));
		}
		return this;
	}
	public plusActiveRecord add(String key,String val){
		this.add(key,val,true);
		return this;
	}
	public plusActiveRecord add(String key,String setVal,String defaultVal){
		return this.add(key,StringUtils.isEmpty(setVal)?defaultVal:setVal);
		//return this;
	}
	public plusActiveRecord add(String key,String setVal,String defaultVal,Boolean scope){
		return this.add(key,StringUtils.isEmpty(setVal)?defaultVal:setVal,scope);
		//return this;
	}

	// - LIKE
	/**
	 * Like closure
	 * 
	 * @param column
	 * @param value
	 * @param math
	 *            | L, R, [default B]: %Left, Right% OR %Both%
	 * @return
	 */
	public plusActiveRecord like(String column, String value, String math) {

		switch (math.toUpperCase()) {
		case "L":
			value = "%" + value;
			break;
		case "R":
			value = value + "%";
			break;
		default:
			value = "%" + value + "%";
			break;
		}

		this._like.put(column, "'" + value + "'");
		return this;
	}

	public plusActiveRecord like(String column, String value) {
		return this.like(column, value, "B");
	}

	// - OR LIKE
	/**
	 * Like closure
	 * 
	 * @param column
	 * @param value
	 * @param math
	 *            | L, R, [default B]: %Left, Right% OR %Both%
	 * @return
	 */
	public plusActiveRecord or_like(String column, String value, String math) {

		switch (math.toUpperCase()) {
		case "L":
			value = "%" + value;
			break;
		case "R":
			value = value + "%";
			break;
		default:
			value = "%" + value + "%";
			break;
		}

		this._or_like.put(column, "'" + value + "'");
		return this;
	}

	public plusActiveRecord or_like(String column, String value) {
		return this.or_like(column, value, "B");
	}

	// - LIMIT
	public plusActiveRecord limit(int limit) {
		return this.limit(limit, 0);
	}

	public plusActiveRecord limit(int limit, int offset) {
		this._limit = String.valueOf(offset) + "," + String.valueOf(limit);
		return this;
	}

	// - ORDER BY
	public plusActiveRecord order(String syntax) {
		this._order_by = syntax;
		return this;
	}

	public plusActiveRecord order(String column, String value) {
		this._order_by = column + " " + value;
		return this;
	}

	// - GROUP BY
	public plusActiveRecord group(String syntax) {
		this._group_by = syntax;
		return this;
	}

	public plusActiveRecord group(String column, String value) {
		this._group_by = column + " " + value;
		return this;
	}
	public plusActiveRecord batchItem(List<HashMap<String,String>> listObjects){
		this.batchItem =listObjects;
		return this;
	}

	public plusActiveRecord batchItem(List<HashMap<String,String>> listObjects,HashMap<String,String> addObjects) {
		this.batchItem =listObjects;
		this.batchAdd =addObjects;
		return this;
	}

	/**
	 * 사용안함
	 * 
	 * @param str
	 */
	public void setDbPrefix(String str) {
		this._db_prefix = str;
	}


	private String getParams(List<? extends Serializable> params,
			boolean withQuotes) {

		StringBuilder sb = new StringBuilder();

		for (Serializable param : params) {

			if (withQuotes) {

				if (param instanceof String) {
					sb.append(String.format("\'%s\',", param));
				} else {
					sb.append(String.format("%s,", param));
				}

			} else {
				sb.append(String.format("%s,", param));
			}

		}
		return sb.substring(0, sb.length() - 1);
	}


    public CommonResultEntity getOut() {

		CommonResultEntity commonResultEntity =new CommonResultEntity();
		switch (flag){
			case inType.INSERT:
				commonResultEntity.setResultCode("INSERT.S");
				commonResultEntity.setResultMsg("등록이 완료되었습니다.");
			break;
			case inType.UPDATE:
				commonResultEntity.setResultCode("UPDATE.S");
				commonResultEntity.setResultMsg("수정이 완료되었습니다.");
			break;
			case inType.DELETE:
				commonResultEntity.setResultCode("UPDATE.S");
				commonResultEntity.setResultMsg("삭제가 완료되었습니다.");
			break;
			case inType.DUP:
				commonResultEntity.setResultCode("DUP");
				commonResultEntity.setResultMsg("저장에 실패했습니다.(UNI.중복된 데이터가 있습니다.)");
			break;
			default:
			case inType.ERR:
				commonResultEntity.setResultCode("ERR");
				commonResultEntity.setResultMsg("저장에 실패했습니다.");
			break;
			case inType.SESS:
				commonResultEntity.setResultCode("SESS");
				commonResultEntity.setResultMsg("세션이 끊겼습니다. 데이터가 유실되었습니다.");
			break;
			case inType.TEMP:
				commonResultEntity.setResultCode("TEMP");
				commonResultEntity.setResultMsg("데이터가 유실되었습니다.");
			break;
				case inType.UNI:
				commonResultEntity.setResultCode("UNI");
				commonResultEntity.setResultMsg("저장에 실패했습니다.(UNI.수정할 데이터가 없습니다.)");
			break;


		}


		return commonResultEntity;
    }
}
