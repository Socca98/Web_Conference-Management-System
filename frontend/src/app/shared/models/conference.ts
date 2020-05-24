export interface Conference {
  id: string;
  name: string;
  website: string;
  startDate: string;
  endDate: string;
  proposalDeadline: string;
  abstractDeadline: string;
  biddingDeadline: string;
  evaluationDeadline: string;
  allowFullPaper: boolean;
  taxFee: number;
  submissions: [];
  users: [];
  nrOfReviewers: number;
}
