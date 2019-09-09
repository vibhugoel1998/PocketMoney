package com.example.vibhu.pocketmoney;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FaqActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        textView=findViewById(R.id.DocReq);
        textView2=findViewById(R.id.DocReq2);
        textView3=findViewById(R.id.DocReq3);

        textView3.setText("4.1 How can I repay the loan?\n" +
                "\n" +
                "You can anytime repay the loan through our mobile app. Else on due date, we use ECS/NACH (National Automated Clearing House) to take repayment automatically. On the repayment date, we will debit your bank account registered with us. Just make sure that you have enough money in your bank account on that day.\n" +"\n"+
                "4.2 From which bank account will my money be deducted/Can I change the bank account from which my money will be deducted?\n" +
                "\n" +
                "Your repayment amount (including all interest and charges) would be deducted only from your salary bank account registered with us. In case you change your salary bank account, pls share new salary bank account statement with at least one salary credit at care@earlysalary.com and we would change the same. You would need to sign new NACH mandate form and once it is registered, we would deduct money from new Salary bank account.\n" +"\n"+
                "4.3 Can I Pre-Pay my loan/Will I be charged for repayment?\n" +
                "\n" +
                "Rejoice! There is no pre-payment fee at the moment. You can pre-pay your loan any time before the repayment date through net banking. Tap the Repay button on the mobile app. You will be charged only for the amount outstanding so by prepaying, you can save money!\n" +"\n"+
                "4.4 Can I Pre-Pay using a credit card?\n" +
                "\n" +
                "Sorry pal! As per RBI guidelines, you can’t pay your dues using a Credit Card.\n" +"\n"+
                "4.5 Can I post pone my repayment date?\n" +
                "\n" +
                "No you can’t! You have to repay on due date only.\n" +"\n"+
                "4.6 What if there isn’t enough money in my salary bank account on my repayment date?\n" +
                "\n" +
                "We suggest that you maintain enough money in bank account as we would send debit instruction to your bank on due date. In case NACH is bounced, we levy NACH/ECS charges for every bounce instance and you may also be levied with interest on delayed payment.\n" +"\n"+
                "4.7 Is repayment through EMIs possible?\n" +
                "\n" +
                "No, once a due date is chosen, we do not allow repayment in EMI.\n" +"\n"+
                "4.8 What happens if I do not repay on time? Does PocketMoney report to the Credit bureaus?\n" +
                "\n" +
                "Yes, our lending partners are RBI registered NBFC/Bank, they are obligated to report our Loan, its history, the late payment and non-payment of dues to credit bureaus such as CIBIL and Equifax. This will impact your chances of getting banking products. By paying on time, your score improves and so does your chances of getting a credit card or any loan!!!\n" +"\n"+
                "4.8 What happens if I have a complaint and I wish to escalate it?\n" +
                "\n" +
                "Oh ho! We are sorry to see you in this situation. Our team would like to help you ASAP to put a smile back to your face. You can also ping us to chat with us on the App.");

        textView2.setText("3.1 What is the minimum and maximum loan amount?\n" +
                "\n" +
                "You can borrow from Rs. 8,000/- to Rs. 100,000/- in multiples of Rs. 1,000.\n" +"\n"+
                "3.2 What is the minimum and maximum loan period /How is the repayment date determined/Why don't I see all the dates in the repayment date option?\n" +
                "\n" +
                "You can apply for loans ranging 7 to 43 days. We would like to ensure that you avoid defaulting your repayment and lowering your score. Hence your options to choose a repayment date would be limited to a few working days on/after your salary date. However, if you have money in your account and are ready to repay your loan you can do so at no additional cost!\n" +"\n"+
                "3.3 If I Prepay, will I still be charged full interest and How do I repay early.\n" +
                "\n" +
                "Is it even a question, we believe in fair and transparent pricing, in case you want to pay early, you will pay interest only till the date you pay. You can easily pay whenever you want on App by clicking repay option.\n" +"\n"+
                "3.4 What are various ways, I can prepay or repay on App\n" +
                "\n" +
                "The best option to prepay is to come on App and repay on or before Due date either through UPI or Netbanking. It will provide you instant credit to your account and you pay interest ONLY till the date on which you repay. In case you donot pay us on or before de date, we will initiate debit instruction to collect funds. We donot accept cheque and cash.\n" +"\n"+
                "3.5 How many loans can I take at a time?\n" +
                "\n" +
                "Currently we allow only one loan at a time\n" +"\n"+
                "3.6 Can I borrow more money on my existing loan/Can I Top-Up my loan account?\n" +
                "\n" +
                "No, we do not allow top up loan as of now. You can prepay your existing loan without any additional cost and take new loan with full sanctioned amount.\n" +"\n"+
                "3.7 How many loans can I take in a year/Why should I wait between two loans for 5 days?\n" +
                "\n" +
                "We allow you to take as many loan as you want in a year. Further, as per our internal policy there should be a minimum gap of 5 days between the repayment date of your current loan and the application date of your next loan.\n" +"\n"+
                "3.8 How much is a loan going to cost me?\n" +
                "\n" +
                "We do not deduct upfront any fee or interest from your loan disbursement amount. Isn't that good news to start with…? Use the sliders on our app to see the amount real time. If you still haven't downloaded the app yet, you can play with the sliders here. The fees and charges include:\n" +
                "1) Processing Fees:\n" +
                "\n" +
                "Processing Fee for the Loan shall not exceed\n" +
                "Loan Amount (Rs.)\n" +
                "First Loan (Rs.)\n" +
                "Second Loan (Rs.)\n" +
                "Subsequent Loans (Rs.)\n" +
                "8,000 to 9,999\n" +
                "150\n" +
                "150\n" +
                "150\n" +
                "10,000 to 29,999\n" +
                "300\n" +
                "200\n" +
                "200\n" +
                "30,000 to 59,999\n" +
                "600\n" +
                "300\n" +
                "300\n" +
                "60,000 to 1,00,000\n" +
                "1.5% of the loan amount\n" +
                "500\n" +
                "500\n" +
                "2) Late payment charges\n" +
                "\n" +
                "In case of delay in payment of Amount Due, we will charge a late payment fee not exceeding 3% per month on the amount due, subject to minimum of Rs. 500.\n" +
                "3) Repayment Instrument(s) mandate reject charge:\n" +
                "\n" +
                "In the event, the repayment instrument(s) mandate registration is rejected, EarlySalary.com will charge rejection charges not exceeding Rs. 250/- per rejection.\n" +
                "4) Charges for bouncing of the Repayment Instrument(s):\n" +
                "\n" +
                "In case of default by reason of the Repayment Instrument(s) being dishonored, EarlySalary.com will charge Rs. 500/- per month / per default towards its dishonor.\n" +
                "5) Prepayment Charges:\n" +
                "\n" +
                "Not exceeding 2% of the Prepaid Amount. But rejoice! Currently we will not charge for prepayment.\n" +
                "6) Stamp Duty:\n" +
                "\n" +
                "Stamp duty – 0.1% of the sanction amount or Rs. 100, whichever is higher. This is charged only on first loan\n" +
                "7) Interest Rate\n" +
                "\n" +
                "Our general interest rate is 2.5% p.m. But we strongly recommend you to connect us with your company HR to negotiate better rates by tieing up with us All charges/Fees are subject to GST and will be charged extra at the prevailing GST rate. Current GST rate is 18%\n" +
                "8) Overdue Interest\n" +
                "\n" +
                "Overdue interest will be charged at 30% p.a. on total overdue amount on daily basis. Overdue balance means principal outstanding and due interest.\n" +
                "3.9 How can I apply for a loan/What is the application process/How much time will you take?\n" +
                "\n" +
                "With your social logins, you will hardly have to key in 5 data points, upload few images– all of which should not take you over a few minutes. At your desired time we will come to your house/office to get your signature and we'll transfer the money right away. If you need funds again in Future and want to take a loan from us, money would reach your account in not more than a few minutes. While applying the first time you will go through these 5 steps:\n" +
                "1) Quickly skim through the mostly prefilled app pages, key in the minimal data left, and upload your bank statements. Quickly rehearse your moves for the night or pack your bags for a hell of a trip. In not more than 10 minutes your loan would be sanctioned.\n" +
                "\n" +
                "2) Once sanctioned, Upload KYC documents and selfie on our mobile app. In minutes we will e-mail you your loan agreement and will ask you to confirm a time and place to sign your loan agreement (RBI needs this!).\n" +
                "\n" +
                "3) Once you confirm your availability, we will come running to you, within an hour or at the time you choose.\n" +
                "\n" +
                "4)Once our officer reaches your house/office, he will check an original ID proof (one that you submitted) and will get your signatures on loan agreement.\n" +
                "\n" +
                "5) Sit in your car and start your engine for one of the best moments of your life. We would have transferred the money to your bank account. The money would reach your account according to the time your bank takes!\n" +
                "\n" +
                "6) Within few minutes of you signing the agreement, you can transfer the loan to your bank account using our mobile app. Within a minutes money is transferred to your bank account once you initiate the money transfer from our mobile app We recommend you to use eKYC service of Aadhaar available on App for faster completion of documentation to avail loan. The whole process should not take more than a Business Days' time to complete.\n" +
                "\n" +"\n"+
                "3.10 Why should I sign the agreement only at my residence/office?\n" +
                "\n" +
                "To add to your comfort, to validate your address we'll have to visit you at either your residence or office only at the desired time.\n" +"\n"+
                "3.11 Why should I show an original ID to the Early Salary Officer who visits me?\n" +
                "\n" +
                "We'll need to inspect the original ID to validate your identity and to ensure that fraudsters don't harm an innocent citizen.\n" +"\n"+
                "3.12 To which account will my loan be disbursed/Can I change the account to which I can get the money?\n" +
                "\n" +
                "Your loan will be transferred to your salary bank account registered with us. In case you change your salary bank account, pls share new salary bank account statement with at least one salary credit at care@earlysalary.com and we would change the same.\n" +"\n"+
                "3.13 Can I apply for loans and get money on holidays?\n" +
                "\n" +
                "Our first level underwriting is done on real time basis by machine hence you may get approval on holidays as well but money transfer can happen only after you complete KYC and loan agreement signing. In case machine is not able to read information or need more data to decide, someone need to look at your case to decide. However, if you have a loan sanctioned and documentation completed, you may be able to access it on Holidays too. Apply right away and get your loan sanctioned!");

        textView.setText("Documents required and their acceptable formats\n" +"\n"+
                "2.1 ID Proofs and Bank Documents required and why?\n" +
                "\n" +
                "We care about YOU and the environment! We do not require any original documents or any photocopies. Upload your best selfie and an image each of these original documents: 1. PAN card 2. Passport/Aadhaar card/Driver's license 3. Cancelled cheque leaf of your salary bank account 4. Salary account bank statement of the last 3 completed calendar months\n" +"\n"+
                "2.2 Why should I only submit images of an original ID or document?\n" +
                "\n" +
                "Taking photos of photos/photocopies could take our machines and employees more time to read your data and Images might not be legible, leading to delay in processing or rejection of application.\n" +"\n"+
                "2.3 Why should I provide details only of my Salary Bank Account?\n" +
                "\n" +
                "We need your salary bank account statements to validate your salary data and to assist you with repayment dates close to your salary date so that you may never have to default on your repayment. To make your life simpler, we have functionality which allow you to provide us the bank statement directly from the bank without looking for pdf statement received from the bank. Simply login to your bank via Perfios ( Our partner in providing better customer experience).\n" +"\n"+
                "2.4 What is login through Bank to provide bank statement and is it safe?\n" +
                "\n" +
                "We strive to provide best service so that you do not need to look for details in your mailbox. We have tied up with a partner which help us in providing you a seamless experience. We or our partner do not save your bank ID and password.\n" +"\n"+
                "2.5 What should I ensure while uploading documents?\n" +
                "\n" +
                "Please make sure that your images are 1. Complete 2. Adequately zoomed 3. Not blurry and 4. Taken with good lighting Additionally, for bank statements are acceptable in pdf formats only which you receive from bank as monthly statement or which you can download from bank website.\n" +"\n"+
                "2.6 In what formats can I upload my documents?\n" +
                "\n" +
                "Bank statement : Bank statements can be uploaded in PDF format only. If your PDF bank statement is password protected, please share your password for us to open and read the bank statement and we will keep all your data safe and secure! KYC documents: Aadhar/PAN/passport/driving license/voters id proof can be uploaded in JPEG or PNG format. We recommend clicking the image using App camera as it help us in compressing better and upload.");
    }

    public void OpenDocsRequired(View view){
        if(textView.getLayoutParams().height== ActionBar.LayoutParams.WRAP_CONTENT){
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        }else{
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        }
    }

    public void OpenDocsRequired2(View view){
        if(textView2.getLayoutParams().height== ActionBar.LayoutParams.WRAP_CONTENT){
            textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        }else{
            textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        }
    }

    public void OpenDocsRequired3(View view){
        if(textView3.getLayoutParams().height== ActionBar.LayoutParams.WRAP_CONTENT){
            textView3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        }else{
            textView3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        }
    }

    public void doFinish7(View view){
        finish();
    }
}
