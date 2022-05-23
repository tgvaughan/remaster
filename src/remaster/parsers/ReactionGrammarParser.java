// Generated from ReactionGrammar.g4 by ANTLR 4.7.2
package remaster.parsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReactionGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, ZERO=6, NZINT=7, NNFLOAT=8, IDENT=9, 
		COMMENT_SINGLELINE=10, COMMENT_MULTILINE=11, WHITESPACE=12;
	public static final int
		RULE_reaction = 0, RULE_reactants = 1, RULE_products = 2, RULE_popsum = 3, 
		RULE_popel = 4, RULE_factor = 5, RULE_id = 6, RULE_popname = 7, RULE_loc = 8, 
		RULE_locidx = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"reaction", "reactants", "products", "popsum", "popel", "factor", "id", 
			"popname", "loc", "locidx"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'+'", "':'", "'['", "']'", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ZERO", "NZINT", "NNFLOAT", "IDENT", 
			"COMMENT_SINGLELINE", "COMMENT_MULTILINE", "WHITESPACE"
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

	@Override
	public String getGrammarFileName() { return "ReactionGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReactionGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ReactionContext extends ParserRuleContext {
		public ReactantsContext reactants() {
			return getRuleContext(ReactantsContext.class,0);
		}
		public ProductsContext products() {
			return getRuleContext(ProductsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ReactionGrammarParser.EOF, 0); }
		public ReactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterReaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitReaction(this);
		}
	}

	public final ReactionContext reaction() throws RecognitionException {
		ReactionContext _localctx = new ReactionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			reactants();
			setState(21);
			match(T__0);
			setState(22);
			products();
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactantsContext extends ParserRuleContext {
		public PopsumContext popsum() {
			return getRuleContext(PopsumContext.class,0);
		}
		public ReactantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterReactants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitReactants(this);
		}
	}

	public final ReactantsContext reactants() throws RecognitionException {
		ReactantsContext _localctx = new ReactantsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reactants);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			popsum();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProductsContext extends ParserRuleContext {
		public PopsumContext popsum() {
			return getRuleContext(PopsumContext.class,0);
		}
		public ProductsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_products; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterProducts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitProducts(this);
		}
	}

	public final ProductsContext products() throws RecognitionException {
		ProductsContext _localctx = new ProductsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_products);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			popsum();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopsumContext extends ParserRuleContext {
		public List<PopelContext> popel() {
			return getRuleContexts(PopelContext.class);
		}
		public PopelContext popel(int i) {
			return getRuleContext(PopelContext.class,i);
		}
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public PopsumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popsum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopsum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopsum(this);
		}
	}

	public final PopsumContext popsum() throws RecognitionException {
		PopsumContext _localctx = new PopsumContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_popsum);
		int _la;
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NZINT:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				popel();
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(30);
					match(T__1);
					setState(31);
					popel();
					}
					}
					setState(36);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				match(ZERO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopelContext extends ParserRuleContext {
		public PopnameContext popname() {
			return getRuleContext(PopnameContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public LocContext loc() {
			return getRuleContext(LocContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public PopelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopel(this);
		}
	}

	public final PopelContext popel() throws RecognitionException {
		PopelContext _localctx = new PopelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_popel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NZINT) {
				{
				setState(40);
				factor();
				}
			}

			setState(43);
			popname();
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(44);
				loc();
				}
			}

			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(47);
				match(T__2);
				setState(48);
				id();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(NZINT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ZERO) | (1L << NZINT) | (1L << IDENT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PopnameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public PopnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_popname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterPopname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitPopname(this);
		}
	}

	public final PopnameContext popname() throws RecognitionException {
		PopnameContext _localctx = new PopnameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_popname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocContext extends ParserRuleContext {
		public LocidxContext locidx() {
			return getRuleContext(LocidxContext.class,0);
		}
		public LocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterLoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitLoc(this);
		}
	}

	public final LocContext loc() throws RecognitionException {
		LocContext _localctx = new LocContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_loc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__3);
			setState(58);
			locidx();
			setState(59);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocidxContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(ReactionGrammarParser.ZERO, 0); }
		public TerminalNode NZINT() { return getToken(ReactionGrammarParser.NZINT, 0); }
		public TerminalNode IDENT() { return getToken(ReactionGrammarParser.IDENT, 0); }
		public LocidxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locidx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterLocidx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitLocidx(this);
		}
	}

	public final LocidxContext locidx() throws RecognitionException {
		LocidxContext _localctx = new LocidxContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_locidx);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ZERO) | (1L << NZINT) | (1L << IDENT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\7\5#\n\5\f\5\16\5&\13\5"+
		"\3\5\5\5)\n\5\3\6\5\6,\n\6\3\6\3\6\5\6\60\n\6\3\6\3\6\5\6\64\n\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16"+
		"\20\22\24\2\3\4\2\b\t\13\13\2<\2\26\3\2\2\2\4\33\3\2\2\2\6\35\3\2\2\2"+
		"\b(\3\2\2\2\n+\3\2\2\2\f\65\3\2\2\2\16\67\3\2\2\2\209\3\2\2\2\22;\3\2"+
		"\2\2\24?\3\2\2\2\26\27\5\4\3\2\27\30\7\3\2\2\30\31\5\6\4\2\31\32\7\2\2"+
		"\3\32\3\3\2\2\2\33\34\5\b\5\2\34\5\3\2\2\2\35\36\5\b\5\2\36\7\3\2\2\2"+
		"\37$\5\n\6\2 !\7\4\2\2!#\5\n\6\2\" \3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2"+
		"\2\2%)\3\2\2\2&$\3\2\2\2\')\7\b\2\2(\37\3\2\2\2(\'\3\2\2\2)\t\3\2\2\2"+
		"*,\5\f\7\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-/\5\20\t\2.\60\5\22\n\2/.\3\2"+
		"\2\2/\60\3\2\2\2\60\63\3\2\2\2\61\62\7\5\2\2\62\64\5\16\b\2\63\61\3\2"+
		"\2\2\63\64\3\2\2\2\64\13\3\2\2\2\65\66\7\t\2\2\66\r\3\2\2\2\678\t\2\2"+
		"\28\17\3\2\2\29:\7\13\2\2:\21\3\2\2\2;<\7\6\2\2<=\5\24\13\2=>\7\7\2\2"+
		">\23\3\2\2\2?@\t\2\2\2@\25\3\2\2\2\7$(+/\63";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}