package eatyourbeets.cards.animator.series.Elsword;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Elesis extends AnimatorCard implements CustomSavable<Elesis.Form>
{
    public enum Form
    {
        None,
        Saber,
        Pyro,
        Soar,
    }

    public static final EYBCardData DATA = Register(Elesis.class)
            .SetAttack(-2, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Elesis(Form.Saber, false), true);
                data.AddPreview(new Elesis(Form.Pyro, false), true);
                data.AddPreview(new Elesis(Form.Soar, false), true);
            });

    private Form currentForm;

    public Elesis()
    {
        this(Form.None, false);
    }

    private Elesis(Form form, boolean upgraded)
    {
        super(DATA);

        this.upgraded = upgraded;
        ChangeForm(form);
    }

    @Override
    public EYBCardPreview GetCardPreview()
    {
        return currentForm != Form.None ? null : super.GetCardPreview();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        int amount = CombatStats.Affinities.GetAffinityLevel(Affinity.Red) +
                CombatStats.Affinities.GetAffinityLevel(Affinity.Green) + CombatStats.Affinities.GetAffinityLevel(Affinity.Blue);

        switch (currentForm)
        {
            case Saber:
            {
                GameActions.Bottom.GainBlock(magicNumber * amount);
                break;
            }

            case Pyro:
            {
                int burning_amount = amount / 2;
                GameActions.Bottom.ApplyBurning(p, m, burning_amount).SkipIfZero(true);
                break;
            }

            case Soar:
            {
                if (CheckSpecialCondition(false))
                {
                    GameActions.Bottom.ChannelRandomOrb(magicNumber);
                }
                break;
            }
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        return currentForm == Form.None ? new Elesis(Form.None, upgraded) : super.makeStatEquivalentCopy();
    }

    @Override
    public AbstractCard makeCopy()
    {
        if (currentForm == Form.None && GameUtilities.InBattle(true) && GameUtilities.GetMasterDeckInstance(uuid) == null)
        {
            int roll = rng.random(0, 2);
            if (roll == 0)
            {
                return new Elesis(Form.Saber, false);
            }
            else if (roll == 1)
            {
                return new Elesis(Form.Pyro, false);
            }
            else
            {
                return new Elesis(Form.Soar, false);
            }
        }

        return new Elesis(currentForm, false);
    }

    @Override
    public Form onSave()
    {
        return currentForm;
    }

    @Override
    public void onLoad(Form form)
    {
        ChangeForm(form == null ? Form.None : form);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (currentForm == Form.None)
        {
            final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            group.group.add(new Elesis(Form.Saber, upgraded));
            group.group.add(new Elesis(Form.Pyro, upgraded));
            group.group.add(new Elesis(Form.Soar, upgraded));

            GameActions.Bottom.SelectFromPile(name, 1, group)
            .SetOptions(false, false)
            .AddCallback(cards ->
            {
                if (cards != null && cards.size() > 0)
                {
                    Elesis card = (Elesis) cards.get(0);

                    ChangeForm(card.currentForm);

                    final AbstractCard inDeck = GameUtilities.GetMasterDeckInstance(uuid);
                    if (inDeck != null)
                    {
                        ((Elesis) inDeck).ChangeForm(currentForm);
                    }
                }
            });
        }
    }

    protected void ChangeForm(Form form)
    {
        if (this.currentForm == form)
        {
            return;
        }

        this.currentForm = form;

        switch (form)
        {
            case None:
            {
                LoadImage(null);

                affinities.Clear();
                SetAffinity_White(1);

                cardText.OverrideDescription(null, true);
                this.isCostModified = this.isCostModifiedForTurn = false;
                this.cost = this.costForTurn = -2;

                break;
            }

            case Saber:
            {
                LoadImage("_Saber");

                Initialize(9, 0, 2, 0);
                SetUpgrade(0, 0, 1, 0);
                SetExhaust(true);

                affinities.Clear();
                SetAffinity_Green(2);
                SetAffinity_White(1);

                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[0], true);
                this.isCostModified = this.isCostModifiedForTurn = false;
                this.cost = this.costForTurn = 2;

                break;
            }

            case Pyro:
            {
                LoadImage("_Pyro");

                Initialize(6, 0, 2);
                SetUpgrade(4, 0, 0);

                affinities.Clear();
                SetAffinity_Red(1);
                SetAffinity_White(2);

                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[1], true);
                this.isCostModified = this.isCostModifiedForTurn = false;
                this.cost = this.costForTurn = 1;

                break;
            }

            case Soar:
            {
                LoadImage("_Soar");

                Initialize(1, 0, 2);
                SetUpgrade(1, 0, 1);

                affinities.Clear();
                SetAffinity_Blue(1, 0, 1);
                SetAffinity_White(1, 0, 1);

                SetExhaust(true);
                SetAffinityRequirement(Affinity.Red, 2);
                SetAffinityRequirement(Affinity.Green, 2);
                SetAffinityRequirement(Affinity.Blue, 2);

                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[2], true);
                this.isCostModified = this.isCostModifiedForTurn = false;
                this.cost = this.costForTurn = 0;

                break;
            }
        }

        if (upgraded)
        {
            upgraded = false;
            upgrade();
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return GameUtilities.GetAllInBattleCopies(Elesis.DATA.ID).size() > 0 && super.CheckSpecialCondition(tryUse);
    }
}