import org.voltdb.VoltProcedure;
import org.voltdb.SQLStmt;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.RegressionModelPrediction;

public class NewLoan_ML_Proc extends VoltProcedure {

	public long run(double id, String member_id, double loan_amnt, double funded_amnt, double funded_amnt_inv, String term, String int_rate, double installment, String grade, String sub_grade, String emp_title, String emp_length, String home_ownership, double annual_inc, String verification_status, long issue_d, double loan_status, String pymnt_plan, String url, String desc, String purpose, String title, String zip_code, String addr_state, double dti, double delinq_2yrs, long earliest_cr_line, double fico_range_low, double fico_range_high, double inq_last_6mths, String mths_since_last_delinq, String mths_since_last_record, double open_acc, double pub_rec, double revol_bal, String revol_util, double total_acc, String initial_list_status, double out_prncp, double out_prncp_inv, double total_pymnt, double total_pymnt_inv, double total_rec_prncp, double total_rec_int, double total_rec_late_fee, double recoveries, double collection_recovery_fee, long last_pymnt_d, double last_pymnt_amnt, long next_pymnt_d, long last_credit_pull_d, double last_fico_range_high, double last_fico_range_low, double collections_12_mths_ex_med, String mths_since_last_major_derog, double policy_code, String application_type, String annual_inc_joint, String dti_joint, String verification_status_joint, double acc_now_delinq, double tot_coll_amt, double tot_cur_bal, double open_acc_6m, double open_act_il, double open_il_12m, double open_il_24m, double mths_since_rcnt_il, double total_bal_il, double il_util, double open_rv_12m, double open_rv_24m, double max_bal_bc, double all_util, double total_rev_hi_lim, double inq_fi, double total_cu_tl, double inq_last_12m, double acc_open_past_24mths, double avg_cur_bal, double bc_open_to_buy, double bc_util, double chargeoff_within_12_mths, double delinq_amnt, double mo_sin_old_il_acct, double mo_sin_old_rev_tl_op, double mo_sin_rcnt_rev_tl_op, double mo_sin_rcnt_tl, double mort_acc, double mths_since_recent_bc, String mths_since_recent_bc_dlq, double mths_since_recent_inq, String mths_since_recent_revol_delinq, double num_accts_ever_120_pd, double num_actv_bc_tl, double num_actv_rev_tl, double num_bc_sats, double num_bc_tl, double num_il_tl, double num_op_rev_tl, double num_rev_accts, double num_rev_tl_bal_gt_0, double num_sats, double num_tl_120dpd_2m, double num_tl_30dpd, double num_tl_90g_dpd_24m, double num_tl_op_past_12m, double pct_tl_nvr_dlq, double percent_bc_gt_75, double pub_rec_bankruptcies, double tax_liens, double tot_hi_cred_lim, double total_bal_ex_mort, double total_bc_limit, double total_il_high_credit_limit, String revol_bal_joint, String sec_app_fico_range_low, String sec_app_fico_range_high, long sec_app_earliest_cr_line, String sec_app_inq_last_6mths, String sec_app_mort_acc, String sec_app_open_acc, String sec_app_revol_util, String sec_app_open_act_il, String sec_app_num_rev_accts, String sec_app_chargeoff_within_12_mths, String sec_app_collections_12_mths_ex_med, String sec_app_mths_since_last_major_derog, String hardship_flag, String hardship_type, String hardship_reason, String hardship_status, String deferral_term, String hardship_amount, long hardship_start_date, long hardship_end_date, long payment_plan_start_date, String hardship_length, String hardship_dpd, String hardship_loan_status, String orig_projected_additional_accrued_interest, String hardship_payoff_balance_amount, String hardship_last_payment_amount, String disbursement_method, String debt_settlement_flag, long debt_settlement_flag_date, String settlement_status, long settlement_date, String settlement_amount, String settlement_percentage, String settlement_term) {
		
        hex.genmodel.GenModel rawModel;
        try {
        rawModel = (hex.genmodel.GenModel) Class.forName("drf_086f889c_fb72_4dc4_a6e4_cd662c6a6a1a").newInstance();
			EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);
        
        RowData row = new RowData();
        row.put("id", id);
        
        RegressionModelPrediction p = null;
			try {
				p = model.predictRegression(row);
			} catch (PredictException e) {
				e.printStackTrace();
			}
        
        System.out.println("prediction = " + p);
        
        } catch(ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}
}
