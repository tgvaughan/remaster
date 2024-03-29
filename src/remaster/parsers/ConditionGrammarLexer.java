// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ConditionGrammar.g4 by ANTLR 4.10.1
package remaster.parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConditionGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, SUM=5, MIN=6, MAX=7, ADD=8, SUB=9, MUL=10, 
		DIV=11, MOD=12, AND=13, OR=14, EQ=15, GT=16, LT=17, GE=18, LE=19, NE=20, 
		ZERO=21, NZINT=22, NNFLOAT=23, IDENT=24, COMMENT_SINGLELINE=25, COMMENT_MULTILINE=26, 
		WHITESPACE=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "SUM", "MIN", "MAX", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "AND", "OR", "EQ", "GT", "LT", "GE", "LE", "NE", "ZERO", 
			"NZINT", "NNFLOAT", "D", "NZD", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", 
			"WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'sum'", "'min'", "'max'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'&&'", "'||'", "'=='", "'>'", "'<'", "'>='", "'<='", 
			"'!='", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "SUM", "MIN", "MAX", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "AND", "OR", "EQ", "GT", "LT", "GE", "LE", "NE", "ZERO", 
			"NZINT", "NNFLOAT", "IDENT", "COMMENT_SINGLELINE", "COMMENT_MULTILINE", 
			"WHITESPACE"
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


	public ConditionGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ConditionGrammar.g4"; }

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
		"\u0004\u0000\u001b\u00bb\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0005\u0015t\b\u0015\n\u0015\f\u0015w\t\u0015"+
		"\u0001\u0016\u0001\u0016\u0003\u0016{\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0005\u0016\u007f\b\u0016\n\u0016\f\u0016\u0082\t\u0016\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u0086\b\u0016\u0001\u0016\u0004\u0016\u0089\b\u0016"+
		"\u000b\u0016\f\u0016\u008a\u0003\u0016\u008d\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0005\u0019\u0095"+
		"\b\u0019\n\u0019\f\u0019\u0098\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u009e\b\u001a\n\u001a\f\u001a\u00a1\t\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u00ab\b\u001b\n\u001b\f\u001b\u00ae\t\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0004\u001c\u00b6\b\u001c\u000b\u001c\f\u001c\u00b7\u0001\u001c\u0001"+
		"\u001c\u0002\u009f\u00ac\u0000\u001d\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00001\u00003\u00185\u00197\u001a"+
		"9\u001b\u0001\u0000\u0006\u0002\u0000EEee\u0001\u000009\u0001\u000019"+
		"\u0003\u0000AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u00c2\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u00003"+
		"\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000"+
		"\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000\u0000"+
		"\u0003=\u0001\u0000\u0000\u0000\u0005?\u0001\u0000\u0000\u0000\u0007A"+
		"\u0001\u0000\u0000\u0000\tC\u0001\u0000\u0000\u0000\u000bG\u0001\u0000"+
		"\u0000\u0000\rK\u0001\u0000\u0000\u0000\u000fO\u0001\u0000\u0000\u0000"+
		"\u0011Q\u0001\u0000\u0000\u0000\u0013S\u0001\u0000\u0000\u0000\u0015U"+
		"\u0001\u0000\u0000\u0000\u0017W\u0001\u0000\u0000\u0000\u0019Y\u0001\u0000"+
		"\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000\u001d_\u0001\u0000\u0000"+
		"\u0000\u001fb\u0001\u0000\u0000\u0000!d\u0001\u0000\u0000\u0000#f\u0001"+
		"\u0000\u0000\u0000%i\u0001\u0000\u0000\u0000\'l\u0001\u0000\u0000\u0000"+
		")o\u0001\u0000\u0000\u0000+q\u0001\u0000\u0000\u0000-z\u0001\u0000\u0000"+
		"\u0000/\u008e\u0001\u0000\u0000\u00001\u0090\u0001\u0000\u0000\u00003"+
		"\u0092\u0001\u0000\u0000\u00005\u0099\u0001\u0000\u0000\u00007\u00a6\u0001"+
		"\u0000\u0000\u00009\u00b5\u0001\u0000\u0000\u0000;<\u0005(\u0000\u0000"+
		"<\u0002\u0001\u0000\u0000\u0000=>\u0005)\u0000\u0000>\u0004\u0001\u0000"+
		"\u0000\u0000?@\u0005[\u0000\u0000@\u0006\u0001\u0000\u0000\u0000AB\u0005"+
		"]\u0000\u0000B\b\u0001\u0000\u0000\u0000CD\u0005s\u0000\u0000DE\u0005"+
		"u\u0000\u0000EF\u0005m\u0000\u0000F\n\u0001\u0000\u0000\u0000GH\u0005"+
		"m\u0000\u0000HI\u0005i\u0000\u0000IJ\u0005n\u0000\u0000J\f\u0001\u0000"+
		"\u0000\u0000KL\u0005m\u0000\u0000LM\u0005a\u0000\u0000MN\u0005x\u0000"+
		"\u0000N\u000e\u0001\u0000\u0000\u0000OP\u0005+\u0000\u0000P\u0010\u0001"+
		"\u0000\u0000\u0000QR\u0005-\u0000\u0000R\u0012\u0001\u0000\u0000\u0000"+
		"ST\u0005*\u0000\u0000T\u0014\u0001\u0000\u0000\u0000UV\u0005/\u0000\u0000"+
		"V\u0016\u0001\u0000\u0000\u0000WX\u0005%\u0000\u0000X\u0018\u0001\u0000"+
		"\u0000\u0000YZ\u0005&\u0000\u0000Z[\u0005&\u0000\u0000[\u001a\u0001\u0000"+
		"\u0000\u0000\\]\u0005|\u0000\u0000]^\u0005|\u0000\u0000^\u001c\u0001\u0000"+
		"\u0000\u0000_`\u0005=\u0000\u0000`a\u0005=\u0000\u0000a\u001e\u0001\u0000"+
		"\u0000\u0000bc\u0005>\u0000\u0000c \u0001\u0000\u0000\u0000de\u0005<\u0000"+
		"\u0000e\"\u0001\u0000\u0000\u0000fg\u0005>\u0000\u0000gh\u0005=\u0000"+
		"\u0000h$\u0001\u0000\u0000\u0000ij\u0005<\u0000\u0000jk\u0005=\u0000\u0000"+
		"k&\u0001\u0000\u0000\u0000lm\u0005!\u0000\u0000mn\u0005=\u0000\u0000n"+
		"(\u0001\u0000\u0000\u0000op\u00050\u0000\u0000p*\u0001\u0000\u0000\u0000"+
		"qu\u00031\u0018\u0000rt\u0003/\u0017\u0000sr\u0001\u0000\u0000\u0000t"+
		"w\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000"+
		"\u0000v,\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000x{\u00050\u0000"+
		"\u0000y{\u0003+\u0015\u0000zx\u0001\u0000\u0000\u0000zy\u0001\u0000\u0000"+
		"\u0000{|\u0001\u0000\u0000\u0000|\u0080\u0005.\u0000\u0000}\u007f\u0003"+
		"/\u0017\u0000~}\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000"+
		"\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u008c\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0083\u0085\u0007\u0000\u0000\u0000\u0084\u0086\u0005-\u0000\u0000\u0085"+
		"\u0084\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086"+
		"\u0088\u0001\u0000\u0000\u0000\u0087\u0089\u0003/\u0017\u0000\u0088\u0087"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008d"+
		"\u0001\u0000\u0000\u0000\u008c\u0083\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d.\u0001\u0000\u0000\u0000\u008e\u008f\u0007"+
		"\u0001\u0000\u0000\u008f0\u0001\u0000\u0000\u0000\u0090\u0091\u0007\u0002"+
		"\u0000\u0000\u00912\u0001\u0000\u0000\u0000\u0092\u0096\u0007\u0003\u0000"+
		"\u0000\u0093\u0095\u0007\u0004\u0000\u0000\u0094\u0093\u0001\u0000\u0000"+
		"\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u00974\u0001\u0000\u0000\u0000"+
		"\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009a\u0005/\u0000\u0000\u009a"+
		"\u009b\u0005/\u0000\u0000\u009b\u009f\u0001\u0000\u0000\u0000\u009c\u009e"+
		"\t\u0000\u0000\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u009f\u009d\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0005\n\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0006\u001a\u0000\u0000\u00a56\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0005/\u0000\u0000\u00a7\u00a8\u0005*\u0000\u0000\u00a8"+
		"\u00ac\u0001\u0000\u0000\u0000\u00a9\u00ab\t\u0000\u0000\u0000\u00aa\u00a9"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ae\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00af"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00af\u00b0"+
		"\u0005*\u0000\u0000\u00b0\u00b1\u0005/\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0006\u001b\u0000\u0000\u00b38\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b6\u0007\u0005\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0006\u001c\u0000\u0000\u00ba:\u0001\u0000\u0000\u0000"+
		"\u000b\u0000uz\u0080\u0085\u008a\u008c\u0096\u009f\u00ac\u00b7\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}