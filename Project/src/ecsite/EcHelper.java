package ecsite;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import beans.MyItemDataBeans;

public class EcHelper {
	// 検索結果
	static final String SEARCH_RESULT_PAGE = "/itemSearchResult.jsp";
	// 商品ページ
	static final String ITEM_PAGE = "/item.jsp";
	// TOPページ
	static final String TOP_PAGE = "/top.jsp";
	// エラーページ
	static final String ERROR_PAGE = "/error.jsp";
	// 買い物かごページ
	static final String CART_PAGE = "/cart.jsp";
	// 購入
	static final String BUY_PAGE = "/buy.jsp";
	// 購入完了
	static final String BUY_RESULT_PAGE = "/buyResult.jsp";
	// ユーザー情報
	static final String USER_INFO = "/userInfo.jsp";
	// ユーザー情報更新画面
	static final String USER_INFO_CHANGE = "/userInfoChange.jsp";
	// ユーザー情報更新確認
	static final String USER_INFO_CHANGE_CONFIRM = "/userInfoChangeConfirm.jsp";
	// ユーザー情報更新完了
	static final String USER_PASS_CHANGE = "/userPassChange.jsp";
	// ユーザー購入履歴
	static final String USER_BUY_HISTORY = "/userBuyHistory.jsp";
	// アイテム一覧（管理者）
	static final String M_ITEM_LIST = "masterItemList.jsp";
	// アイテム追加（管理者）
	static final String M_NEW_ITEM = "masterNewItem.jsp";
	// アイテム削除（管理者）
	static final String M_ITEM_DELETE = "masterItemDelete.jsp";
	// アイテム情報更新（管理者）
	static final String M_ITEM_UPDATE = "masterItemUpdate.jsp";
	// ログイン
	static final String LOGIN_PAGE = "/login.jsp";
	// 新規登録
	static final String NEW_USER = "/newUser.jsp";
	// 新規登録入力内容確認
	static final String NEW_USER_CONFIRM = "/newUserConfirm.jsp";
	// 新規登録完了
	static final String NEW_USER_RESULT = "/newUserResult.jsp";

	public static EcHelper getInstance() {
		return new EcHelper();
	}

	/**
	 * 商品の合計金額を算出する
	 *
	 * @param items
	 * @return total
	 */
	public static int getTotalItemPrice(ArrayList<MyItemDataBeans> items) {
		int total = 0;
		for (MyItemDataBeans item : items) {
			total += item.getPrice();
		}
		return total;
	}

	/**
	 * ハッシュ関数
	 *
	 * @param target
	 * @return
	 */
	public static String getSha256(String target) {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				buf.append(String.format("%02x", digest[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * セッションから指定データを取得（削除も一緒に行う）
	 *
	 * @param session
	 * @param str
	 * @return
	 */
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object test = session.getAttribute(str);
		session.removeAttribute(str);

		return test;
	}

	/**
	 * ログインIDのバリデーション
	 *
	 * @param inputLoginId
	 * @return
	 */
	public static boolean isLoginIdValidation(String inputLoginId) {
		// 英数字アンダースコア以外が入力されていたら
		if (inputLoginId.matches("[0-9a-zA-Z-_]+")) {
			return true;
		}

		return false;

	}


}
