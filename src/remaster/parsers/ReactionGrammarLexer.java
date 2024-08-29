// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.13.1
package remaster.parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ReactionGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, ZERO=7, NZINT=8, NNFLOAT=9, 
		IDENT=10, COMMENT_SINGLELINE=11, COMMENT_MULTILINE=12, WHITESPACE=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "ZERO", "NZINT", "NNFLOAT", 
			"D", "NZD", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'+'", "':'", "','", "'['", "']'", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "ZERO", "NZINT", "NNFLOAT", 
			"IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ReactionGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ReactionGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\rx\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0005\u00071\b"+
		"\u0007\n\u0007\f\u00074\t\u0007\u0001\b\u0001\b\u0003\b8\b\b\u0001\b\u0001"+
		"\b\u0005\b<\b\b\n\b\f\b?\t\b\u0001\b\u0001\b\u0003\bC\b\b\u0001\b\u0004"+
		"\bF\b\b\u000b\b\f\bG\u0003\bJ\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0005\u000bR\b\u000b\n\u000b\f\u000bU\t\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f[\b\f\n\f\f\f^\t\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\rh\b\r\n\r\f\rk\t\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0004\u000es\b\u000e\u000b"+
		"\u000e\f\u000et\u0001\u000e\u0001\u000e\u0002\\i\u0000\u000f\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\u0000\u0015\u0000\u0017\n\u0019\u000b\u001b\f\u001d\r"+
		"\u0001\u0000\u0006\u0002\u0000EEee\u0001\u000009\u0001\u000019\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u007f\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003\"\u0001\u0000\u0000\u0000"+
		"\u0005$\u0001\u0000\u0000\u0000\u0007&\u0001\u0000\u0000\u0000\t(\u0001"+
		"\u0000\u0000\u0000\u000b*\u0001\u0000\u0000\u0000\r,\u0001\u0000\u0000"+
		"\u0000\u000f.\u0001\u0000\u0000\u0000\u00117\u0001\u0000\u0000\u0000\u0013"+
		"K\u0001\u0000\u0000\u0000\u0015M\u0001\u0000\u0000\u0000\u0017O\u0001"+
		"\u0000\u0000\u0000\u0019V\u0001\u0000\u0000\u0000\u001bc\u0001\u0000\u0000"+
		"\u0000\u001dr\u0001\u0000\u0000\u0000\u001f \u0005-\u0000\u0000 !\u0005"+
		">\u0000\u0000!\u0002\u0001\u0000\u0000\u0000\"#\u0005+\u0000\u0000#\u0004"+
		"\u0001\u0000\u0000\u0000$%\u0005:\u0000\u0000%\u0006\u0001\u0000\u0000"+
		"\u0000&\'\u0005,\u0000\u0000\'\b\u0001\u0000\u0000\u0000()\u0005[\u0000"+
		"\u0000)\n\u0001\u0000\u0000\u0000*+\u0005]\u0000\u0000+\f\u0001\u0000"+
		"\u0000\u0000,-\u00050\u0000\u0000-\u000e\u0001\u0000\u0000\u0000.2\u0003"+
		"\u0015\n\u0000/1\u0003\u0013\t\u00000/\u0001\u0000\u0000\u000014\u0001"+
		"\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"3\u0010\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000058\u00050\u0000"+
		"\u000068\u0003\u000f\u0007\u000075\u0001\u0000\u0000\u000076\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009=\u0005.\u0000\u0000:<\u0003\u0013"+
		"\t\u0000;:\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000"+
		"\u0000\u0000=>\u0001\u0000\u0000\u0000>I\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000@B\u0007\u0000\u0000\u0000AC\u0005-\u0000\u0000BA\u0001"+
		"\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CE\u0001\u0000\u0000\u0000"+
		"DF\u0003\u0013\t\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000"+
		"GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000"+
		"\u0000I@\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000J\u0012\u0001"+
		"\u0000\u0000\u0000KL\u0007\u0001\u0000\u0000L\u0014\u0001\u0000\u0000"+
		"\u0000MN\u0007\u0002\u0000\u0000N\u0016\u0001\u0000\u0000\u0000OS\u0007"+
		"\u0003\u0000\u0000PR\u0007\u0004\u0000\u0000QP\u0001\u0000\u0000\u0000"+
		"RU\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000T\u0018\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VW\u0005"+
		"/\u0000\u0000WX\u0005/\u0000\u0000X\\\u0001\u0000\u0000\u0000Y[\t\u0000"+
		"\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000\u0000\\]\u0001"+
		"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]_\u0001\u0000\u0000\u0000"+
		"^\\\u0001\u0000\u0000\u0000_`\u0005\n\u0000\u0000`a\u0001\u0000\u0000"+
		"\u0000ab\u0006\f\u0000\u0000b\u001a\u0001\u0000\u0000\u0000cd\u0005/\u0000"+
		"\u0000de\u0005*\u0000\u0000ei\u0001\u0000\u0000\u0000fh\t\u0000\u0000"+
		"\u0000gf\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ij\u0001\u0000"+
		"\u0000\u0000ig\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000lm\u0005*\u0000\u0000mn\u0005/\u0000\u0000no\u0001\u0000"+
		"\u0000\u0000op\u0006\r\u0000\u0000p\u001c\u0001\u0000\u0000\u0000qs\u0007"+
		"\u0005\u0000\u0000rq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000"+
		"\u0000vw\u0006\u000e\u0000\u0000w\u001e\u0001\u0000\u0000\u0000\u000b"+
		"\u000027=BGIS\\it\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}