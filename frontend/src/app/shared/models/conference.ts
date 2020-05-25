import {Submission} from './submission';
import {User} from './user';

export interface Conference {
  id: string;
  name: string;
  website: string;
  startDate: number;
  endDate: number;
  proposalDeadline: number;
  abstractDeadline: number;
  biddingDeadline: number;
  evaluationDeadline: number;
  allowFullPaper: boolean;
  taxFee: number;
  submissions: any[];
  users: User[];
  nrOfReviews: number;
}
