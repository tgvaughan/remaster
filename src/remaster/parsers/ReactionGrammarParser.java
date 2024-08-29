// Generated from /Users/vaughant/code/beast_and_friends/remaster/src/remaster/parsers/ReactionGrammar.g4 by ANTLR 4.13.1
package remaster.parsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ReactionGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, ZERO=7, NZINT=8, NNFLOAT=9, 
		IDENT=10, COMMENT_SINGLELINE=11, COMMENT_MULTILINE=12, WHITESPACE=13;
	public static final int
		RULE_reaction = 0, RULE_reactants = 1, RULE_products = 2, RULE_popsum = 3, 
		RULE_popel = 4, RULE_factor = 5, RULE_id = 6, RULE_singlepopel = 7, RULE_reactlist = 8, 
		RULE_popname = 9, RULE_loc = 10, RULE_locidx = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"reaction", "reactants", "products", "popsum", "popel", "factor", "id", 
			"singlepopel", "reactlist", "popname", "loc", "locidx"
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitReaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactionContext reaction() throws RecognitionException {
		ReactionContext _localctx = new ReactionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_reaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			reactants();
			setState(25);
			match(T__0);
			setState(26);
			products();
			setState(27);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitReactants(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactantsContext reactants() throws RecognitionException {
		ReactantsContext _localctx = new ReactantsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reactants);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitProducts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductsContext products() throws RecognitionException {
		ProductsContext _localctx = new ProductsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_products);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopsum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopsumContext popsum() throws RecognitionException {
		PopsumContext _localctx = new PopsumContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_popsum);
		int _la;
		try {
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NZINT:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				popel();
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(34);
					match(T__1);
					setState(35);
					popel();
					}
					}
					setState(40);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopelContext popel() throws RecognitionException {
		PopelContext _localctx = new PopelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_popel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NZINT) {
				{
				setState(44);
				factor();
				}
			}

			setState(47);
			popname();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(48);
				loc();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(51);
				match(T__2);
				setState(52);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1408L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class SinglepopelContext extends ParserRuleContext {
		public PopnameContext popname() {
			return getRuleContext(PopnameContext.class,0);
		}
		public LocContext loc() {
			return getRuleContext(LocContext.class,0);
		}
		public SinglepopelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singlepopel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterSinglepopel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitSinglepopel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitSinglepopel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SinglepopelContext singlepopel() throws RecognitionException {
		SinglepopelContext _localctx = new SinglepopelContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_singlepopel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			popname();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(60);
				loc();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ReactlistContext extends ParserRuleContext {
		public List<SinglepopelContext> singlepopel() {
			return getRuleContexts(SinglepopelContext.class);
		}
		public SinglepopelContext singlepopel(int i) {
			return getRuleContext(SinglepopelContext.class,i);
		}
		public TerminalNode EOF() { return getToken(ReactionGrammarParser.EOF, 0); }
		public ReactlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).enterReactlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReactionGrammarListener ) ((ReactionGrammarListener)listener).exitReactlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitReactlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactlistContext reactlist() throws RecognitionException {
		ReactlistContext _localctx = new ReactlistContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reactlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			singlepopel();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(64);
				match(T__3);
				setState(65);
				singlepopel();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitPopname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PopnameContext popname() throws RecognitionException {
		PopnameContext _localctx = new PopnameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_popname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitLoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocContext loc() throws RecognitionException {
		LocContext _localctx = new LocContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_loc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__4);
			setState(76);
			locidx();
			setState(77);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReactionGrammarVisitor ) return ((ReactionGrammarVisitor<? extends T>)visitor).visitLocidx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocidxContext locidx() throws RecognitionException {
		LocidxContext _localctx = new LocidxContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_locidx);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1408L) != 0)) ) {
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
		"\u0004\u0001\rR\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003%\b\u0003\n\u0003\f\u0003(\t\u0003\u0001\u0003\u0003\u0003+\b\u0003"+
		"\u0001\u0004\u0003\u0004.\b\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"2\b\u0004\u0001\u0004\u0001\u0004\u0003\u00046\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007>\b"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0005\bC\b\b\n\b\f\bF\t\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0000\u0000\f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0000\u0001\u0002\u0000\u0007\b\n\nL\u0000\u0018\u0001\u0000"+
		"\u0000\u0000\u0002\u001d\u0001\u0000\u0000\u0000\u0004\u001f\u0001\u0000"+
		"\u0000\u0000\u0006*\u0001\u0000\u0000\u0000\b-\u0001\u0000\u0000\u0000"+
		"\n7\u0001\u0000\u0000\u0000\f9\u0001\u0000\u0000\u0000\u000e;\u0001\u0000"+
		"\u0000\u0000\u0010?\u0001\u0000\u0000\u0000\u0012I\u0001\u0000\u0000\u0000"+
		"\u0014K\u0001\u0000\u0000\u0000\u0016O\u0001\u0000\u0000\u0000\u0018\u0019"+
		"\u0003\u0002\u0001\u0000\u0019\u001a\u0005\u0001\u0000\u0000\u001a\u001b"+
		"\u0003\u0004\u0002\u0000\u001b\u001c\u0005\u0000\u0000\u0001\u001c\u0001"+
		"\u0001\u0000\u0000\u0000\u001d\u001e\u0003\u0006\u0003\u0000\u001e\u0003"+
		"\u0001\u0000\u0000\u0000\u001f \u0003\u0006\u0003\u0000 \u0005\u0001\u0000"+
		"\u0000\u0000!&\u0003\b\u0004\u0000\"#\u0005\u0002\u0000\u0000#%\u0003"+
		"\b\u0004\u0000$\"\u0001\u0000\u0000\u0000%(\u0001\u0000\u0000\u0000&$"+
		"\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'+\u0001\u0000\u0000"+
		"\u0000(&\u0001\u0000\u0000\u0000)+\u0005\u0007\u0000\u0000*!\u0001\u0000"+
		"\u0000\u0000*)\u0001\u0000\u0000\u0000+\u0007\u0001\u0000\u0000\u0000"+
		",.\u0003\n\u0005\u0000-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/1\u0003\u0012\t\u000002\u0003\u0014\n\u0000"+
		"10\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000025\u0001\u0000\u0000"+
		"\u000034\u0005\u0003\u0000\u000046\u0003\f\u0006\u000053\u0001\u0000\u0000"+
		"\u000056\u0001\u0000\u0000\u00006\t\u0001\u0000\u0000\u000078\u0005\b"+
		"\u0000\u00008\u000b\u0001\u0000\u0000\u00009:\u0007\u0000\u0000\u0000"+
		":\r\u0001\u0000\u0000\u0000;=\u0003\u0012\t\u0000<>\u0003\u0014\n\u0000"+
		"=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u000f\u0001\u0000"+
		"\u0000\u0000?D\u0003\u000e\u0007\u0000@A\u0005\u0004\u0000\u0000AC\u0003"+
		"\u000e\u0007\u0000B@\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000"+
		"DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EG\u0001\u0000\u0000"+
		"\u0000FD\u0001\u0000\u0000\u0000GH\u0005\u0000\u0000\u0001H\u0011\u0001"+
		"\u0000\u0000\u0000IJ\u0005\n\u0000\u0000J\u0013\u0001\u0000\u0000\u0000"+
		"KL\u0005\u0005\u0000\u0000LM\u0003\u0016\u000b\u0000MN\u0005\u0006\u0000"+
		"\u0000N\u0015\u0001\u0000\u0000\u0000OP\u0007\u0000\u0000\u0000P\u0017"+
		"\u0001\u0000\u0000\u0000\u0007&*-15=D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}