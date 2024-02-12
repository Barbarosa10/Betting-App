import { TeamModel } from './TeamModel';
import {OddsModel} from "./OddsModel";
import {CompetitionModel} from "./CompetitionModel";
import {AreaModel} from "./AreaModel";


export class MatchModel {
  id: number;
  matchday: number;
  stage: string;
  utcDate: string;
  homeTeam: TeamModel;
  awayTeam: TeamModel;
  competition: CompetitionModel;
  odds_generated: OddsModel;
  area: AreaModel;
  sportType: string;

  constructor(id: number, matchday: number, stage: string, utcDate: string, homeTeam: TeamModel, awayTeam: TeamModel,
              competition: CompetitionModel, odds_generated: OddsModel, area: AreaModel,
              sportType: string) {
    this.id = id;
    this.matchday = matchday;
    this.stage = stage;
    this.utcDate = utcDate;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.odds_generated = odds_generated;
    this.competition = competition;
    this.area = area;
    this.sportType = sportType;
  }
}
